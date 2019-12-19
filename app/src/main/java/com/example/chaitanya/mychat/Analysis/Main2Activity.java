package com.example.chaitanya.mychat.Analysis;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chaitanya.mychat.FireBaseLog.SignUpActivity;
import com.example.chaitanya.mychat.FireBaseLog.User;
import com.example.chaitanya.mychat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.services.language.v1.CloudNaturalLanguage;
import com.google.api.services.language.v1.CloudNaturalLanguageRequestInitializer;
import com.google.api.services.language.v1.model.AnnotateTextRequest;
import com.google.api.services.language.v1.model.AnnotateTextResponse;
import com.google.api.services.language.v1.model.Entity;
import com.google.api.services.language.v1.model.Sentiment;
import com.google.api.services.language.v1.model.Features;
import com.google.api.services.language.v1.model.Document;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

        public static final String TAG = "Main2Activity";

    public static final String API_KEY = "AIzaSyC4O1iVYADRmHFyjmnkgVhQiPZeGW59XTs";

    @BindView(R.id.analyze)
    Button analyze;

    @BindView(R.id.sentiment)
    TextView sentiment;

    @BindView(R.id.entity)
    RecyclerView entities;

    @BindView(R.id.docText)
    EditText docText;

    @BindView(R.id.cmp_name)
    EditText cmp_name;

    @BindView(R.id.cmp_email)
    EditText cmp_mail;

    EntityListAdapter entityListAdapter;
    private List<Entity> entityList;

    private CloudNaturalLanguage naturalLanguageService;
    private Document document;

    private Features features;
    String type="";
    LinearLayout send_feedback,view_feedback;
    private DatabaseReference mDatabase,demoRef;
    public String text="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        send_feedback=(LinearLayout)findViewById(R.id.send_feedback);
        view_feedback=(LinearLayout)findViewById(R.id.view_feedback);
        naturalLanguageService = new CloudNaturalLanguage.Builder(
                AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(),
                null
        ).setCloudNaturalLanguageRequestInitializer(
                new CloudNaturalLanguageRequestInitializer(API_KEY)
        ).build();


        type=getIntent().getStringExtra("Type");


        document = new Document();
        document.setType("PLAIN_TEXT");
        document.setLanguage("en-US");

        features = new Features();
        features.setExtractEntities(true);
        features.setExtractSyntax(true);
        features.setExtractDocumentSentiment(true);

        final AnnotateTextRequest request = new AnnotateTextRequest();
        request.setDocument(document);
        request.setFeatures(features);

        entityList = new ArrayList<>();
        entityListAdapter = new EntityListAdapter(entityList);
        entities.setAdapter(entityListAdapter);
        entities.setLayoutManager(new LinearLayoutManager(this));




        if (type.equalsIgnoreCase("send")){
            analyze.setOnClickListener(this);
            send_feedback.setVisibility(View.VISIBLE);
            view_feedback.setVisibility(View.GONE);
        }else {

            send_feedback.setVisibility(View.GONE);
            view_feedback.setVisibility(View.VISIBLE);
            callAPI();

        }




    }

    @Override
    public void onClick(View view) {
        String text = docText.getText().toString().trim();
        if (!TextUtils.isEmpty(text)) {
            document.setContent(text);

            final AnnotateTextRequest request = new AnnotateTextRequest();
            request.setDocument(document);
            request.setFeatures(features);


            FeedbackModel feedbackModel=new FeedbackModel(
                    cmp_name.getText().toString().trim(),
                    cmp_mail.getText().toString().trim(),
                    docText.getText().toString().trim(),
                    "Comp"
            );

            FirebaseDatabase.getInstance().getReference("FeedBack")
                    .setValue(feedbackModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Main2Activity.this, "Thank you for giving feedback", Toast.LENGTH_LONG).show();
                    } else {
                        //display a failure message
                        Toast.makeText(Main2Activity.this, "fail", Toast.LENGTH_LONG).show();

                    }
                }
            });




        }
    }
    public void callAPI() {


        final ArrayList<String> feedback = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //DatabaseReference ref = mDatabase.child("FeedBack");
        //Query phoneQuery = ref.orderByChild("type").equalTo("Comp");
        demoRef = mDatabase.child("FeedBack");

       /* phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    FeedbackModel feedbackModel=singleSnapshot.getValue(FeedbackModel.class);

                    text=feedbackModel.getFeedback();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("tag", "onCancelled", databaseError.toException());
            }
        });*/

        demoRef.child("feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(Main2Activity.this, "" + value, Toast.LENGTH_LONG).show();
                text = value;
                analysisFromApi(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this, "" + databaseError, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void analysisFromApi(String textAna){




        document.setContent(textAna);

        final AnnotateTextRequest request = new AnnotateTextRequest();
        request.setDocument(document);
        request.setFeatures(features);
        new AsyncTask<Object, Void, AnnotateTextResponse>() {
            @Override
            protected AnnotateTextResponse doInBackground(Object... params) {
                AnnotateTextResponse response = null;
                try {
                    response = naturalLanguageService.documents().annotateText(request).execute();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }

            @Override
            protected void onPostExecute(AnnotateTextResponse response) {
                super.onPostExecute(response);
                if (response != null) {


                    Sentiment sent = response.getDocumentSentiment();
                    entityList.addAll(response.getEntities());
                    entityListAdapter.notifyDataSetChanged();
                    sentiment.setText("Score : " + sent.getScore() + " Magnitude : " + sent.getMagnitude());
                }
            }
        }.execute();
    }
}
