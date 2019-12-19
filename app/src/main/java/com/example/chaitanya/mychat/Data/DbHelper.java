package com.example.chaitanya.mychat.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaitanya on 26-03-2018.
 */

import com.example.chaitanya.mychat.Test.Question;

import static com.example.chaitanya.mychat.Data.QiuzContract.MovieEntry.KEY_ANSWER;
import static com.example.chaitanya.mychat.Data.QiuzContract.MovieEntry.KEY_ID;
import static com.example.chaitanya.mychat.Data.QiuzContract.MovieEntry.KEY_OPTA;
import static com.example.chaitanya.mychat.Data.QiuzContract.MovieEntry.KEY_OPTB;
import static com.example.chaitanya.mychat.Data.QiuzContract.MovieEntry.KEY_OPTC;
import static com.example.chaitanya.mychat.Data.QiuzContract.MovieEntry.KEY_QUES;
import static com.example.chaitanya.mychat.Data.QiuzContract.MovieEntry.TABLE_QUEST;
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name

    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {
        Question q1=new Question("Which one of the following is not a prime number?","31", "61 ", "91", "91");
        this.addQuestion(q1);
        Question q2=new Question("What least number must be added to 1056, so that the sum is completely divisible by 23 ?", "2", "3", "18", "2");
        this.addQuestion(q2);
        Question q3=new Question("1397 x 1397 = ? ","1951609", "1981709","1981709", "1951609" );
        this.addQuestion(q3);
        Question q4=new Question("The largest 4 digit number exactly divisible by 88 is:", "9768", "9944", "8888","9944");
        this.addQuestion(q4);
        Question q5=new Question("The sum of first 45 natural numbers is:","1035","2070","2140","1035");
        this.addQuestion(q5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
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
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
