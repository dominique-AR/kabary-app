package com.example.superquiz.model;

public class Question {
    private int id;
    private String questionText;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer; // "A", "B", "C", ou "D"
    private String hint;
    private int difficulty; // 1=facile, 2=moyen, 3=difficile

    public Question() {}

    public Question(int id, String questionText, String answerA, String answerB,
                    String answerC, String answerD, String correctAnswer, String hint, int difficulty) {
        this.id = id;
        this.questionText = questionText;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
        this.hint = hint;
        this.difficulty = difficulty;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getAnswerA() { return answerA; }
    public void setAnswerA(String answerA) { this.answerA = answerA; }

    public String getAnswerB() { return answerB; }
    public void setAnswerB(String answerB) { this.answerB = answerB; }

    public String getAnswerC() { return answerC; }
    public void setAnswerC(String answerC) { this.answerC = answerC; }

    public String getAnswerD() { return answerD; }
    public void setAnswerD(String answerD) { this.answerD = answerD; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public String getHint() { return hint; }
    public void setHint(String hint) { this.hint = hint; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }
}