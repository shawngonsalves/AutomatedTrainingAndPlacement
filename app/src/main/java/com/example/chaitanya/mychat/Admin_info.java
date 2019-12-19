package com.example.chaitanya.mychat;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chaitanya.mychat.FireBaseLog.User;
import com.example.chaitanya.mychat.sql.DatabaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Admin_info extends AppCompatActivity {
TextView pid,name,Email,user;
DatabaseHelper DB= new DatabaseHelper(this);
private String email;
private String Name,E_mail,PID,User;
ImageView iv;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_info);

        pid=(TextView)findViewById(R.id.tv1);
        name=(TextView)findViewById(R.id.tv2);
        Email=(TextView)findViewById(R.id.tv3);
        user=(TextView)findViewById(R.id.tv4);
        iv=(ImageView)findViewById(R.id.pic_prof);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        final String emailFromIntet = getIntent().getStringExtra("NAME");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child("Users");
        Query phoneQuery = ref.orderByChild("email").equalTo(emailFromIntet);


        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    User user_ = singleSnapshot.getValue(User.class);
                    name.setText("Name:"+user_.getName());


                    pid.setText("Student Id:"+user_.getStudent_id());
                    Email.setText("Email id:"+emailFromIntet);
                    user.setText("Date of birth:"+user_.getDob());
                    if (user_.getName().trim().equalsIgnoreCase("Shawn")){
                        iv.setImageResource(R.drawable.admin);
                    }else {
                        iv.setImageResource(R.drawable.student);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("tag", "onCancelled", databaseError.toException());
            }
        });


       /* mDatabase.child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                com.example.chaitanya.mychat.FireBaseLog.User user_ = dataSnapshot.getValue(User.class);
                iv.setImageResource(R.drawable.admin);

                pid.setText(user_.getStudent_id());
                Email.setText(email);
                user.setText(user_.getDob());

                Log.d("tag", "User name: " + user_.getName() + ", email " + user_.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException());
            }
        });
        */

      /*  name.setText(nameFromIntent);
        if(nameFromIntent.equals("Chaitanya")){
            iv.setImageResource(R.drawable.admin);

            pid.setText("141083");
            Email.setText(emailFromIntet);
            user.setText("7977161629");
        }else {
            iv.setImageResource(R.drawable.student);
            pid.setText("141080");
            name.setText(nameFromIntent);
            Email.setText(emailFromIntet);
            user.setText("+918898789856");
        }*/
//        handleIntent();

//        Cursor c = DB.getRecordByEmail(email);
//        PID=c.getString(0);
//        Name=c.getString(1);
//        User=c.getString(3);
//
//        pid.setText(PID);
//        name.setText(Name);
//
//        user.setText(User);


    }
//    private void handleIntent(Intent intent) {
//        String nameFromIntent = getIntent().getStringExtra("EMAIL");
//        DatabaseHelper db =new DatabaseHelper(this);
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(nameFromIntent);
//            Cursor c = db.getInformation(query, null);
//            PID=c.getString(0);
//        Name=c.getString(1);
//        User=c.getString(3);
//
//        pid.setText(PID);
//        name.setText(Name);
//
//        user.setText(User);
//
//
//            //process Cursor and display results
//        }

}
