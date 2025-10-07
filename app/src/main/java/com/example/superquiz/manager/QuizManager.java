package com.example.superquiz.manager;

import com.example.superquiz.model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuizManager {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private List<Boolean> answers; // true = correct, false = incorrect

    public QuizManager(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.answers = new ArrayList<>();
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public boolean hasNextQuestion() {
        return currentQuestionIndex < questions.size() - 1;
    }

    public void nextQuestion() {
        if (hasNextQuestion()) {
            currentQuestionIndex++;
        }
    }

    public void checkAnswer(String selectedAnswer) {
        Question current = getCurrentQuestion();
        if (current != null) {
            boolean isCorrect = current.getCorrectAnswer().equals(selectedAnswer);
            answers.add(isCorrect);
            if (isCorrect) {
                score++;
            }
        }
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionIndex + 1;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getScore() {
        return score;
    }

    public boolean isQuizCompleted() {
        return currentQuestionIndex >= questions.size();
    }

    public double getScorePercentage() {
        if (questions.size() == 0) return 0;
        return (score * 100.0) / questions.size();
    }

    public List<Boolean> getAnswers() {
        return answers;
    }

    public void reset() {
        currentQuestionIndex = 0;
        score = 0;
        answers.clear();
    }
}