package com.example.chaitanya.mychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chaitanya.mychat.Resoningtest.QuizeActivityR;
import com.example.chaitanya.mychat.Test.QuizActivity;

public class SelectTest extends AppCompatActivity {
    Button apti,res,tech,puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);
        apti=(Button)findViewById(R.id.aptitude);
        res=(Button)findViewById(R.id.resoning);
        tech=(Button)findViewById(R.id.tech);
        puzzle=(Button)findViewById(R.id.puz);

        apti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(SelectTest.this, QuizActivity.class);
                startActivity(i);
            }
        });
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectTest.this, QuizeActivityR.class);
                startActivity(i);
            }
        });
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(SelectTest.this, QuizActivity.class);
                startActivity(i);
            }
        });
        puzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectTest.this, QuizeActivityR.class);
                startActivity(i);
            }
        });
    }
}
