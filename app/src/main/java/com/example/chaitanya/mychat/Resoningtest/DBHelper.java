package com.example.chaitanya.mychat.Resoningtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chaitanya.mychat.Resoningtest.QuestionsResoning;

import java.util.ArrayList;
import java.util.List;

import static com.example.chaitanya.mychat.Resoningtest.QUizContract.MovieEntry.KEY_ANSWER;
import static com.example.chaitanya.mychat.Resoningtest.QUizContract.MovieEntry.KEY_ID;
import static com.example.chaitanya.mychat.Resoningtest.QUizContract.MovieEntry.KEY_OPTA;
import static com.example.chaitanya.mychat.Resoningtest.QUizContract.MovieEntry.KEY_OPTB;
import static com.example.chaitanya.mychat.Resoningtest.QUizContract.MovieEntry.KEY_OPTC;
import static com.example.chaitanya.mychat.Resoningtest.QUizContract.MovieEntry.KEY_QUES;
import static com.example.chaitanya.mychat.Resoningtest.QUizContract.MovieEntry.TABLE_QUEST1;

/**
 * Created by Chaitanya on 19-04-2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME_R = "triviaQuizR";
    // tasks table name

    private SQLiteDatabase dbase;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME_R, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST1 + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {
        QuestionsResoning q1=new QuestionsResoning("A person crosses a 600 m long street in 5 minutes. What is his speed in km per hour?","7.2", "8.4", "10", "7.2");
        this.addQuestion(q1);
        QuestionsResoning q2=new QuestionsResoning("An aeroplane covers a certain distance at a speed of 240 kmph in 5 hours. To cover the same distance in 1 hours, it must travel at a speed of:",  "360 kmph", "600 kmph", "720 kmph", "720 kmph");
        this.addQuestion(q2);
        QuestionsResoning q3=new QuestionsResoning("If a person walks at 14 km/hr instead of 10 km/hr, he would have walked 20 km more. The actual distance travelled by him is: ","50 km", "56 km","70 km","50 km");
        this.addQuestion(q3);
        QuestionsResoning q4=new QuestionsResoning("Excluding stoppages, the speed of a bus is 54 kmph and including stoppages, it is 45 kmph. For how many minutes does the bus stop per hour?", "10", "12","20","10");
        this.addQuestion(q4);
        QuestionsResoning q5=new QuestionsResoning("A man complete a journey in 10 hours. He travels first half of the journey at the rate of 21 km/hr and second half at the rate of 24 km/hr. Find the total journey in km. ","224 km","230 km","234 km","224 km");
        this.addQuestion(q5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST1);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(QuestionsResoning quest1) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest1.getQUESTION());
        values.put(KEY_ANSWER, quest1.getANSWER());
        values.put(KEY_OPTA, quest1.getOPTA());
        values.put(KEY_OPTB, quest1.getOPTB());
        values.put(KEY_OPTC, quest1.getOPTC());
        // Inserting Row
        dbase.insert(TABLE_QUEST1, null, values);
    }
    public List<QuestionsResoning> getAllQuestions() {
        List<QuestionsResoning> quesList = new ArrayList<QuestionsResoning>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST1;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                QuestionsResoning quest = new QuestionsResoning();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST1;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}

