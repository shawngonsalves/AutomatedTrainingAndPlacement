package com.example.chaitanya.mychat.Data;

import android.provider.BaseColumns;

/**
 * Created by Chaitanya on 26-03-2018.
 */

public class QiuzContract {
    public static class MovieEntry implements BaseColumns {
        public static final String TABLE_QUEST = "quest";
        // tasks Table Columns names
        public static final String KEY_ID = "id";
        public static final String KEY_QUES = "question";
        public static final String KEY_ANSWER = "answer"; //correct option
        public static final String KEY_OPTA= "opta"; //option a
        public static final String KEY_OPTB= "optb"; //option b
        public static final String KEY_OPTC= "optc"; //option c
        public static final String KEY_OPTD= "optd"; //option d

    }
}
