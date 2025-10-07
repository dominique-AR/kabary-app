package com.example.superquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    private String userName;
    private int score;
    private int total;
    private double percentage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        // Récupérer les arguments
        if (getArguments() != null) {
            userName = getArguments().getString("user_name", "Joueur");
            score = getArguments().getInt("score", 0);
            total = getArguments().getInt("total", 0);
            percentage = getArguments().getDouble("percentage", 0);
        }

        // Initialiser les vues
        TextView titleText = view.findViewById(R.id.result_title);
        TextView scoreText = view.findViewById(R.id.result_score);
        TextView messageText = view.findViewById(R.id.result_message);
        ImageView resultImage = view.findViewById(R.id.result_image);
        Button retryButton = view.findViewById(R.id.retry_quiz_button);
        Button homeButton = view.findViewById(R.id.home_button);

        // Afficher les résultats
        titleText.setText("Bravo " + userName + " !");
        scoreText.setText(score + " / " + total);

        // Message personnalisé selon le score
        String message;
        int imageResource;

        if (percentage >= 80) {
            message = "🎉 Excellent ! Tu es un vrai expert du kabary ! Continue comme ça !";
            imageResource = R.drawable.ic_trophy_gold;
        } else if (percentage >= 60) {
            message = "👍 Très bien ! Tu connais bien le kabary. Encore un petit effort pour devenir expert !";
            imageResource = R.drawable.ic_trophy_silver;
        } else if (percentage >= 40) {
            message = "💪 Pas mal ! Tu as des bases solides. Relis les informations et réessaye !";
            imageResource = R.drawable.ic_trophy_bronze;
        } else {
            message = "📚 Continue d'apprendre ! Le kabary est un art qui demande de la pratique. Réessaye après avoir relu les infos !";
            imageResource = R.drawable.ic_book;
        }

        messageText.setText(message);
        resultImage.setImageResource(imageResource);

        // Bouton recommencer
        retryButton.setOnClickListener(v -> {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, new WelcomeFragment())
                    .commit();
        });

        // Bouton accueil
        homeButton.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack(null,
                    androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, new WelcomeFragment())
                    .commit();
        });

        return view;
    }
}