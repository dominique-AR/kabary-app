package com.example.superquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.superquiz.database.DatabaseHelper;
import com.example.superquiz.manager.QuizManager;
import com.example.superquiz.model.Question;
import java.util.List;

public class QuizFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private QuizManager quizManager;
    private String userName;

    private TextView questionCounter;
    private TextView questionText;
    private TextView hintText;
    private TextView resultText;
    private Button answerA, answerB, answerC, answerD;
    private Button hintButton, validateButton, nextButton;
    private ProgressBar progressBar;
    private Button selectedAnswer;

    private final int BEIGE = Color.parseColor("#D7CCC8");
    private final int BROWN = Color.parseColor("#8D6E63");
    private final int TEXT_DEFAULT = Color.parseColor("#3E2723");
    private final int TEXT_SELECTED = Color.parseColor("#FFFFFF");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        // Récupérer le nom de l'utilisateur
        userName = getArguments() != null ? getArguments().getString("user_name", "Joueur") : "Joueur";

        // Initialiser la base de données
        databaseHelper = new DatabaseHelper(requireContext());
        List<Question> questions = databaseHelper.getAllQuestions();
        quizManager = new QuizManager(questions);

        // Initialiser les vues
        initViews(view);

        // Charger la première question
        loadQuestion();

        // Configurer les listeners
        setupListeners();

        return view;
    }

    private void initViews(View view) {
        questionCounter = view.findViewById(R.id.question_counter);
        progressBar = view.findViewById(R.id.progress_bar);
        questionText = view.findViewById(R.id.question_text);
        hintText = view.findViewById(R.id.hint_text);
        resultText = view.findViewById(R.id.result_text);

        answerA = view.findViewById(R.id.answer_a);
        answerB = view.findViewById(R.id.answer_b);
        answerC = view.findViewById(R.id.answer_c);
        answerD = view.findViewById(R.id.answer_d);

        hintButton = view.findViewById(R.id.hint_button);
        validateButton = view.findViewById(R.id.validate_button);
        nextButton = view.findViewById(R.id.next_button);

        validateButton.setEnabled(false);
    }

    private void loadQuestion() {
        Question current = quizManager.getCurrentQuestion();
        if (current == null) {
            showResults();
            return;
        }

        // Mettre à jour le compteur et la progression
        questionCounter.setText("Question " + quizManager.getCurrentQuestionNumber() +
                " / " + quizManager.getTotalQuestions());
        progressBar.setProgress((int)((quizManager.getCurrentQuestionNumber() * 100.0) /
                quizManager.getTotalQuestions()));

        // Afficher la question
        questionText.setText(current.getQuestionText());
        answerA.setText("A. " + current.getAnswerA());
        answerB.setText("B. " + current.getAnswerB());
        answerC.setText("C. " + current.getAnswerC());
        answerD.setText("D. " + current.getAnswerD());
        hintText.setText("💡 " + current.getHint());

        // Réinitialiser l'interface
        resetQuestionUI();
    }

    private void setupListeners() {
        // Listeners pour les réponses
        setAnswerClickListener(answerA, "A");
        setAnswerClickListener(answerB, "B");
        setAnswerClickListener(answerC, "C");
        setAnswerClickListener(answerD, "D");

        // Bouton indice
        hintButton.setOnClickListener(v -> {
            hintText.setVisibility(View.VISIBLE);
            hintButton.setEnabled(false);
        });

        // Bouton valider
        validateButton.setOnClickListener(v -> validateAnswer());

        // Bouton suivant
        nextButton.setOnClickListener(v -> {
            if (quizManager.hasNextQuestion()) {
                quizManager.nextQuestion();
                loadQuestion();
            } else {
                showResults();
            }
        });
    }

    private void setAnswerClickListener(Button button, String answer) {
        button.setOnClickListener(v -> {
            // Désélectionner la réponse précédente
            if (selectedAnswer != null) {
                selectedAnswer.setBackgroundColor(BEIGE);
                selectedAnswer.setTextColor(TEXT_DEFAULT);
            }

            // Sélectionner la nouvelle réponse
            button.setBackgroundColor(BROWN);
            button.setTextColor(TEXT_SELECTED);
            selectedAnswer = button;
            button.setTag(answer);

            // Activer le bouton valider
            validateButton.setEnabled(true);
        });
    }

    private void validateAnswer() {
        if (selectedAnswer == null) return;

        String userAnswer = (String) selectedAnswer.getTag();
        Question current = quizManager.getCurrentQuestion();

        quizManager.checkAnswer(userAnswer);

        boolean isCorrect = current.getCorrectAnswer().equals(userAnswer);
        resultText.setVisibility(View.VISIBLE);

        if (isCorrect) {
            resultText.setText("🎉 Bonne réponse, " + userName + " !");
            resultText.setTextColor(Color.parseColor("#2E7D32"));
            selectedAnswer.setBackgroundColor(Color.parseColor("#4CAF50"));
        } else {
            resultText.setText("❌ Mauvaise réponse... La bonne réponse était : " + current.getCorrectAnswer());
            resultText.setTextColor(Color.parseColor("#C62828"));
            selectedAnswer.setBackgroundColor(Color.parseColor("#F44336"));

            // Montrer la bonne réponse
            highlightCorrectAnswer(current.getCorrectAnswer());
        }

        // Désactiver les interactions
        disableAnswers();
        validateButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.VISIBLE);
    }

    private void highlightCorrectAnswer(String correctAnswer) {
        Button correctButton = null;
        if (correctAnswer.equals("A")) correctButton = answerA;
        else if (correctAnswer.equals("B")) correctButton = answerB;
        else if (correctAnswer.equals("C")) correctButton = answerC;
        else if (correctAnswer.equals("D")) correctButton = answerD;

        if (correctButton != null && correctButton != selectedAnswer) {
            correctButton.setBackgroundColor(Color.parseColor("#4CAF50"));
            correctButton.setTextColor(TEXT_SELECTED);
        }
    }

    private void disableAnswers() {
        answerA.setEnabled(false);
        answerB.setEnabled(false);
        answerC.setEnabled(false);
        answerD.setEnabled(false);
        hintButton.setEnabled(false);
    }

    private void resetQuestionUI() {
        // Réinitialiser la sélection
        selectedAnswer = null;

        // Réinitialiser tous les boutons
        answerA.setBackgroundColor(BEIGE);
        answerA.setTextColor(TEXT_DEFAULT);
        answerA.setEnabled(true);

        answerB.setBackgroundColor(BEIGE);
        answerB.setTextColor(TEXT_DEFAULT);
        answerB.setEnabled(true);

        answerC.setBackgroundColor(BEIGE);
        answerC.setTextColor(TEXT_DEFAULT);
        answerC.setEnabled(true);

        answerD.setBackgroundColor(BEIGE);
        answerD.setTextColor(TEXT_DEFAULT);
        answerD.setEnabled(true);

        // Cacher les éléments
        resultText.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);
        validateButton.setVisibility(View.VISIBLE);
        hintText.setVisibility(View.GONE);

        // Réactiver les boutons
        hintButton.setEnabled(true);
        validateButton.setEnabled(false);
    }

    private void showResults() {
        Bundle args = new Bundle();
        args.putString("user_name", userName);
        args.putInt("score", quizManager.getScore());
        args.putInt("total", quizManager.getTotalQuestions());
        args.putDouble("percentage", quizManager.getScorePercentage());

        ResultFragment resultFragment = new ResultFragment();
        resultFragment.setArguments(args);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_view, resultFragment)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}