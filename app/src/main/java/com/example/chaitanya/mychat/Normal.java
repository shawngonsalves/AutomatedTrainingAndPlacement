package com.example.chaitanya.mychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.chaitanya.mychat.Analysis.Main2Activity;
import com.example.chaitanya.mychat.FireBaseLog.LoginActivity;
import com.example.chaitanya.mychat.Message.MainActivity;
import com.example.chaitanya.mychat.Test.QuizActivity;
import com.example.chaitanya.mychat.UploadFile.MainUploadActivity;
import com.example.chaitanya.mychat.UploadFile.ViewUploadActivity;
import com.example.chaitanya.mychat.UploadResume.MainUploadResume;
import com.example.chaitanya.mychat.UploadResume.ViewResume;
import com.example.chaitanya.mychat.sql.DatabaseHelper;

public class Normal extends AppCompatActivity {
TextView tv,tv1,tv2;
private String nameFromIntent;
private String typeFromIntent;
private String type;
    DatabaseHelper helper=new DatabaseHelper(this);

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        nameFromIntent = getIntent().getStringExtra("EMAIL");

        typeFromIntent=getIntent().getStringExtra("TYPE");
        tv1 = (TextView) findViewById(R.id.tv1);
        tv=(TextView) findViewById(R.id.tv2);
        tv.setText(nameFromIntent);
        tv1.setText(typeFromIntent);

//        String username=helper.UserName(nameFromIntent);
//        tv2=(TextView)findViewById(R.id.tv2);
//        tv2.setText(username);


    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (typeFromIntent.equals("Admin")) {
            if (item.getItemId() == R.id.profile) {
                Intent intent = new Intent(Normal.this, Admin_info.class);
                //intent.putExtra("EMAIL", email);
                intent.putExtra("EMAIL", nameFromIntent);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.postresult) {
                Intent intent = new Intent(Normal.this, MainUploadActivity.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }

            if (item.getItemId() == R.id.m_feedback) {
                Intent intent = new Intent(Normal.this,Main2Activity.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }


            if (item.getItemId() == R.id.m_ViewResume) {
                Intent intent = new Intent(Normal.this, ViewResume.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }
//            if (item.getItemId() == R.id.Chat) {
//                Intent intent = new Intent(Normal.this, MainActivity.class);
//                //intent.putExtra("EMAIL", email);
//                intent.putExtra("EMAIL", nameFromIntent);
//
//                startActivity(intent);
//            }
            if (item.getItemId() == R.id.menu_sign_out) {
                Intent intent = new Intent(Normal.this, LoginActivity.class);

                //intent.putExtra("EMAIL", email);

                startActivity(intent);
                finish();
            }
        }else {
            if (item.getItemId() == R.id.profile1) {
                Intent intent = new Intent(Normal.this, Admin_info.class);
                //intent.putExtra("EMAIL", email);
                intent.putExtra("EMAIL", nameFromIntent);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.m_test) {
                Intent intent = new Intent(Normal.this, SelectTest.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }
            if (item.getItemId() == R.id.m_ViewResult) {
                Intent intent = new Intent(Normal.this, ViewUploadActivity.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }
            if (item.getItemId() == R.id.m_uploadResume) {
                Intent intent = new Intent(Normal.this, MainUploadResume.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }

        }
//        if (item.getItemId() == R.id.Chat) {
//            Intent intent = new Intent(Normal.this, MainActivity.class);
//            //intent.putExtra("EMAIL", email);
//            intent.putExtra("EMAIL", nameFromIntent);
//
//            startActivity(intent);
//        }
            if (item.getItemId() == R.id.menu_sign_out) {
            Intent intent = new Intent(Normal.this, LoginActivity.class);
            //intent.putExtra("EMAIL", email);

            startActivity(intent);
            finish();
        }


        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (typeFromIntent.equals("Admin")) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
        }else {
            getMenuInflater().inflate(R.menu.student_menu, menu);
        }
        return true;
    }
}
