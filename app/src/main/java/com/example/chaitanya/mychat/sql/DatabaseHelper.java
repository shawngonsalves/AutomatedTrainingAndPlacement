package com.example.chaitanya.mychat.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chaitanya.mychat.model.User;

/**
 * Created by Chaitanya on 01-04-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;

    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_PID = "user_PID";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_TYPE = "user_type";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_MOBILE_NO = "user_mono";
    private static final String COLUMN_USER_DOB = "user_dob";



    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_PID + " INTEGER, " + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_TYPE + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_MOBILE_NO + " NUMBER,"
            + COLUMN_USER_DOB + " DATE" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_PID, user.getPID());
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_TYPE, user.getType());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_MOBILE_NO, user.getMobileNum());
        values.put(COLUMN_USER_DOB, user.getDob());


        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password , String type) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?" + " AND " + COLUMN_USER_TYPE + " =?";
        String[] selectionArgs = {email, password, type};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

//    public String searchType(String email) {
//        String[] columns = {
//                COLUMN_USER_ID
//        };
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT " + COLUMN_USER_EMAIL + " , " + COLUMN_USER_TYPE + " FROM " + TABLE_USER;
//        Cursor cursor = db.rawQuery(query, null);
//        String a, b;
//        b = "Not Found";
//
//
//        if (cursor.moveToFirst()) {
//            do {
//                a = cursor.getString(0);
//
//
//                if (a.equals(email)) {
//                    b = cursor.getString(2);
//
//                    break;
//                }
//            }
//            while (cursor.moveToNext());
//        }
//        return b;
//    }


//    public void getRecordByEmail(String email) {
//
//
//    }

    public String UserName(String email) {
        String[] columns = {
                COLUMN_USER_ID
        };
        Cursor cursor = null;
        String userName = "";
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            cursor =db.rawQuery("SELECT * FROM Employee WHERE " + COLUMN_USER_EMAIL + " = " + email + ";", new String[]{email + ""});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                userName = cursor.getString(cursor.getColumnIndex("UserName"));
            }
            return userName;
        } finally {
            cursor.close();
        }
    }



//    public String Mobile(String email){
//       // return mobileNum;
//    }
//
//    public String DOB(String email){
//       // return dob;
//    }
//



    public Cursor getInformation(String email, String[] columns){
        SQLiteDatabase db = this.getWritableDatabase();

        String Selection = "SELECT * FROM user WHERE user_email ='" + email + "';";
        Cursor cursor= db.rawQuery(Selection, null);
        if (cursor != null){
            cursor.moveToNext();

        }
        return cursor;
    }

}
//    Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_EMAIL + " = '"+
//            email +"'", null);
//         c.moveToFirst();
//                 return c;

