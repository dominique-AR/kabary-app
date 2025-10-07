package com.example.superquiz;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WelcomeFragment extends Fragment {

    private EditText nameInput;
    private Button startQuizButton;
    private Button discoverButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        nameInput = view.findViewById(R.id.name_input);
        startQuizButton = view.findViewById(R.id.start_quiz_button);
        discoverButton = view.findViewById(R.id.discover_button);

        // Désactiver le bouton quiz au départ
        startQuizButton.setEnabled(false);

        // Activer le bouton quiz quand il y a du texte
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean hasText = s != null && s.toString().trim().length() > 0;
                startQuizButton.setEnabled(hasText);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Bouton "Découvrir" - vers les pages d'info
        discoverButton.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, new KabaryInfoPagerFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // Bouton pour commencer le quiz
        startQuizButton.setOnClickListener(v -> {
            startQuizButton.setEnabled(false);

            String name = nameInput.getText().toString().trim();

            if (!name.isEmpty()) {
                Bundle args = new Bundle();
                args.putString("user_name", name);

                QuizFragment quizFragment = new QuizFragment();
                quizFragment.setArguments(args);

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_view, quizFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
            } else {
                startQuizButton.setEnabled(true);
            }
        });

        return view;
    }
}