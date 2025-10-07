package com.example.superquiz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.superquiz.model.Question;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mpikabary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_QUESTIONS = "questions";
    private static final String COL_ID = "id";
    private static final String COL_QUESTION = "question_text";
    private static final String COL_ANSWER_A = "answer_a";
    private static final String COL_ANSWER_B = "answer_b";
    private static final String COL_ANSWER_C = "answer_c";
    private static final String COL_ANSWER_D = "answer_d";
    private static final String COL_CORRECT = "correct_answer";
    private static final String COL_HINT = "hint";
    private static final String COL_DIFFICULTY = "difficulty";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_QUESTION + " TEXT,"
                + COL_ANSWER_A + " TEXT,"
                + COL_ANSWER_B + " TEXT,"
                + COL_ANSWER_C + " TEXT,"
                + COL_ANSWER_D + " TEXT,"
                + COL_CORRECT + " TEXT,"
                + COL_HINT + " TEXT,"
                + COL_DIFFICULTY + " INTEGER"
                + ")";
        db.execSQL(CREATE_TABLE);

        // Insérer les questions initiales
        insertInitialQuestions(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    private void insertInitialQuestions(SQLiteDatabase db) {
        insertQuestion(db,
                "Quelle est la nature principale du kabary dans la culture malgache ?",
                "Un artisanat de vannerie typique des Hautes Terres",
                "Un chant improvisé sur la vie quotidienne",
                "Un art oratoire traditionnel et codifié",
                "Un type de danse rituelle",
                "C",
                "Pensez à la manière dont les messages sont transmis lors des grands événements publics à Madagascar.",
                1
        );

        insertQuestion(db,
                "À quelle époque le kabary était-il réservé aux rois et chefs de clan ?",
                "Au XIXe siècle",
                "Au XVIe siècle",
                "Au XXe siècle",
                "À l'époque précoloniale",
                "D",
                "Le kabary était un outil politique autant que social dans le royaume merina.",
                2
        );

        insertQuestion(db,
                "Quel roi a institutionnalisé le kabary au royaume merina ?",
                "Andrianampoinimerina",
                "Radama I",
                "Ranavalona I",
                "Ralambo",
                "D",
                "Ce roi a structuré le kabary au IXe siècle selon certaines traditions.",
                3
        );

        insertQuestion(db,
                "Quand le kabary a-t-il été inscrit au patrimoine immatériel de l'UNESCO ?",
                "2015",
                "2021",
                "2018",
                "2010",
                "B",
                "C'est une reconnaissance récente de cet art oratoire unique.",
                2
        );

        insertQuestion(db,
                "Combien d'étapes comporte une véritable partition orale du kabary ?",
                "5 étapes",
                "8 étapes",
                "10 étapes",
                "6 étapes",
                "B",
                "Du Tari-dresaka jusqu'au Fisaorana sy Famaranana.",
                2
        );

        insertQuestion(db,
                "Dans quels événements le kabary est-il utilisé aujourd'hui ?",
                "Uniquement les mariages",
                "Mariages, funérailles et cérémonies officielles",
                "Seulement les cérémonies religieuses",
                "Uniquement lors des fêtes nationales",
                "B",
                "Le kabary reste très présent dans la vie sociale et politique malgache.",
                1
        );
    }

    private void insertQuestion(SQLiteDatabase db, String question, String a, String b,
                                String c, String d, String correct, String hint, int difficulty) {
        ContentValues values = new ContentValues();
        values.put(COL_QUESTION, question);
        values.put(COL_ANSWER_A, a);
        values.put(COL_ANSWER_B, b);
        values.put(COL_ANSWER_C, c);
        values.put(COL_ANSWER_D, d);
        values.put(COL_CORRECT, correct);
        values.put(COL_HINT, hint);
        values.put(COL_DIFFICULTY, difficulty);
        db.insert(TABLE_QUESTIONS, null, values);
    }

    // Récupérer toutes les questions
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUESTIONS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question();
                q.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
                q.setQuestionText(cursor.getString(cursor.getColumnIndexOrThrow(COL_QUESTION)));
                q.setAnswerA(cursor.getString(cursor.getColumnIndexOrThrow(COL_ANSWER_A)));
                q.setAnswerB(cursor.getString(cursor.getColumnIndexOrThrow(COL_ANSWER_B)));
                q.setAnswerC(cursor.getString(cursor.getColumnIndexOrThrow(COL_ANSWER_C)));
                q.setAnswerD(cursor.getString(cursor.getColumnIndexOrThrow(COL_ANSWER_D)));
                q.setCorrectAnswer(cursor.getString(cursor.getColumnIndexOrThrow(COL_CORRECT)));
                q.setHint(cursor.getString(cursor.getColumnIndexOrThrow(COL_HINT)));
                q.setDifficulty(cursor.getInt(cursor.getColumnIndexOrThrow(COL_DIFFICULTY)));
                questions.add(q);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return questions;
    }

    // Récupérer une question par ID
    public Question getQuestionById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_QUESTIONS, null, COL_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        Question question = null;
        if (cursor != null && cursor.moveToFirst()) {
            question = new Question();
            question.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
            question.setQuestionText(cursor.getString(cursor.getColumnIndexOrThrow(COL_QUESTION)));
            question.setAnswerA(cursor.getString(cursor.getColumnIndexOrThrow(COL_ANSWER_A)));
            question.setAnswerB(cursor.getString(cursor.getColumnIndexOrThrow(COL_ANSWER_B)));
            question.setAnswerC(cursor.getString(cursor.getColumnIndexOrThrow(COL_ANSWER_C)));
            question.setAnswerD(cursor.getString(cursor.getColumnIndexOrThrow(COL_ANSWER_D)));
            question.setCorrectAnswer(cursor.getString(cursor.getColumnIndexOrThrow(COL_CORRECT)));
            question.setHint(cursor.getString(cursor.getColumnIndexOrThrow(COL_HINT)));
            question.setDifficulty(cursor.getInt(cursor.getColumnIndexOrThrow(COL_DIFFICULTY)));
            cursor.close();
        }
        return question;
    }

    // Compter le nombre de questions
    public int getQuestionCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_QUESTIONS, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }
}