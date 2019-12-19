package com.example.chaitanya.mychat.Message;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chaitanya.mychat.Admin_info;
import com.example.chaitanya.mychat.Analysis.Main2Activity;
import com.example.chaitanya.mychat.FireBaseLog.LoginActivity;
import com.example.chaitanya.mychat.FireBaseLog.User;
import com.example.chaitanya.mychat.Normal;
import com.example.chaitanya.mychat.R;
import com.example.chaitanya.mychat.SelectTest;
import com.example.chaitanya.mychat.UploadFile.MainUploadActivity;
import com.example.chaitanya.mychat.UploadFile.ViewUploadActivity;
import com.example.chaitanya.mychat.UploadResume.MainUploadResume;
import com.example.chaitanya.mychat.UploadResume.ViewResume;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.BuildConfig;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final String MESSAGE_LENGTH_KEY = "message_length";
    public static final int RC_SIGN_IN = 1;
    private static final int RC_PHOTO_PICKER = 2;

    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    private String mUsername;
    private String name_display;
    private String email;




    //Firebase Instance Variables

    private FirebaseDatabase database;
    private DatabaseReference mFirebaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mChatPhotosReference;
    private FirebaseRemoteConfig mRemoteConfig;
    String email_user="";

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsername = ANONYMOUS;


        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(MainActivity.this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();



        email_user=getIntent().getStringExtra("email");




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.getMenu().clear();




        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               /* int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.up_resume:
                        Intent intent=new Intent(MainActivity.this, MainUploadResume.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "up resume",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.up_result:
                        Intent intent_result=new Intent(MainActivity.this, MainUploadActivity.class);
                        startActivity(intent_result);
                        Toast.makeText(MainActivity.this, "Up result",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.view_resume:
                        Intent intent_vr=new Intent(MainActivity.this, ViewResume.class);
                        startActivity(intent_vr);
                        Toast.makeText(MainActivity.this, "View Resume",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.view_result:
                        Intent intent_vresult=new Intent(MainActivity.this, ViewUploadActivity.class);
                        startActivity(intent_vresult);
                        Toast.makeText(MainActivity.this, "View resuly",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.mycart:
                        Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.logout:
                        signOut();
                        return true;

                    default:
                        return true;
                }
                */
                //name_display = "Chaitanya";


                if (name_display.trim().equals("Shawn")) {
                    if (item.getItemId() == R.id.profile) {
                        Intent intent = new Intent(MainActivity.this, Admin_info.class);
                        intent.putExtra("EMAIL", email_user);
                        intent.putExtra("NAME", email);

                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.postresult) {
                        Intent intent = new Intent(MainActivity.this, MainUploadActivity.class);
                        //intent.putExtra("EMAIL", email);
                        startActivity(intent);
                    }

                    if (item.getItemId() == R.id.m_feedback) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("Type", email);
                        startActivity(intent);
                    }


                    if (item.getItemId() == R.id.m_ViewResume) {
                        Intent intent = new Intent(MainActivity.this, ViewResume.class);
                        //intent.putExtra("EMAIL", email);

                        startActivity(intent);
                    }


                    if (item.getItemId() == R.id.menu_sign_out) {
                        AuthUI.getInstance().signOut(MainActivity.this);

                        //intent.putExtra("EMAIL", email);


                    }




                } else {
                    if (item.getItemId() == R.id.profile1) {
                        Intent intent = new Intent(MainActivity.this, Admin_info.class);
                        intent.putExtra("EMAIL", name_display);
                        intent.putExtra("NAME", email);


                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.m_test) {
                        Intent intent = new Intent(MainActivity.this, SelectTest.class);
                        //intent.putExtra("EMAIL", email);

                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.m_ViewResult) {
                        Intent intent = new Intent(MainActivity.this, ViewUploadActivity.class);
                        //intent.putExtra("EMAIL", email);

                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.m_feedback) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("Type", email);
                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.m_uploadResume) {
                        Intent intent = new Intent(MainActivity.this, MainUploadResume.class);
                        //intent.putExtra("EMAIL", email);

                        startActivity(intent);
                    }
                    if (item.getItemId() == R.id.menu_sign_out) {
                        AuthUI.getInstance().signOut(MainActivity.this);
                        //intent.putExtra("EMAIL", email);


                    }

                }



                return true;

            }

        });


        //Initializing Firebase Object
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();
        mFirebaseReference = database.getReference().child("messages");
        mChatPhotosReference = mFirebaseStorage.getReference().child("chat_photos");
        mRemoteConfig = FirebaseRemoteConfig.getInstance();

        // Initialize references to views
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMessageListView = (ListView) findViewById(R.id.messageListView);
        mPhotoPickerButton = (ImageButton) findViewById(R.id.photoPickerButton);
        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);

        // Initialize message ListView and its adapter
        final List<FriendlyMessage> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // ImagePickerButton shows an image picker to upload a image for a message
        mPhotoPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Complete action using"), RC_PHOTO_PICKER);
            }
        });

        // Enable Send button when there's text to send
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FriendlyMessage message = new FriendlyMessage(mMessageEditText.getText().toString(), mUsername, null);
                mFirebaseReference.push().setValue(message);

                // Clear input box
                mMessageEditText.setText("");


            }
        });

        //Authenticating Users
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //user signed-in

                    //name_display = user.getDisplayName();
                    email = user.getEmail();

                        // The user's ID, unique to the Firebase project. Do NOT use
                        // this value to authenticate with your backend server, if
                        // you have one. Use User.getToken() instead.


                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference ref = mDatabase.child("Users");
                    Query phoneQuery = ref.orderByChild("email").equalTo(email);


                    phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                                User user_ = singleSnapshot.getValue(User.class);
                                OnSignedIn(user_.getName());
                                name_display=user_.getName();
                                if (name_display.trim().equals("Shawn")) {
                                    nv.getMenu().clear();
                                    nv.inflateMenu(R.menu.main_menu);
                                }else{
                                    nv.getMenu().clear();
                                    nv.inflateMenu(R.menu.student_menu);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("tag", "onCancelled", databaseError.toException());
                        }
                    });



                    Toast.makeText(MainActivity.this, "You are Signed-in.Welcome to Friendly Chat App.",Toast.LENGTH_SHORT).show();
                } else {
                    //user signed-out
                    /*OnSignedOut();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER
                                           )
                                    .build(),
                            RC_SIGN_IN);
                    name_display="kewal";
                    */
                    Intent i=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        };



        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();

        mRemoteConfig.setConfigSettings(configSettings);

        //Creating Default Config map
        Map<String, Object> defaultConfigMap = new HashMap<>();
        defaultConfigMap.put(MESSAGE_LENGTH_KEY, DEFAULT_MSG_LENGTH_LIMIT);
        mRemoteConfig.setDefaults(defaultConfigMap);

        fetchConfig();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       /* if (name_display.equals("Chaitanya")) {
            if (item.getItemId() == R.id.profile) {
                Intent intent = new Intent(MainActivity.this, Admin_info.class);
                intent.putExtra("EMAIL", name_display);
                intent.putExtra("NAME", email);

                startActivity(intent);
            }
            if (item.getItemId() == R.id.postresult) {
                Intent intent = new Intent(MainActivity.this, MainUploadActivity.class);
                //intent.putExtra("EMAIL", email);
                startActivity(intent);
            }

            if (item.getItemId() == R.id.m_feedback) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                //intent.putExtra("EMAIL", email);
                startActivity(intent);
            }


            if (item.getItemId() == R.id.m_ViewResume) {
                Intent intent = new Intent(MainActivity.this, ViewResume.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }


            if (item.getItemId() == R.id.menu_sign_out) {
                AuthUI.getInstance().signOut(this);
                //intent.putExtra("EMAIL", email);


            }
        }else {
            if (item.getItemId() == R.id.profile1) {
                Intent intent = new Intent(MainActivity.this, Admin_info.class);
                intent.putExtra("EMAIL", name_display);
                intent.putExtra("NAME", email);



                startActivity(intent);
            }
            if (item.getItemId() == R.id.m_test) {
                Intent intent = new Intent(MainActivity.this, SelectTest.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }
            if (item.getItemId() == R.id.m_ViewResult) {
                Intent intent = new Intent(MainActivity.this, ViewUploadActivity.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }
            if (item.getItemId() == R.id.m_feedback) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                //intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
            if (item.getItemId() == R.id.m_uploadResume) {
                Intent intent = new Intent(MainActivity.this, MainUploadResume.class);
                //intent.putExtra("EMAIL", email);

                startActivity(intent);
            }
            if (item.getItemId() == R.id.menu_sign_out) {
                AuthUI.getInstance().signOut(this);
                //intent.putExtra("EMAIL", email);


            }

        }






*/


//
//        switch (item.getItemId()){
//            case R.id.sign_out_menu:
//                //signout
//                AuthUI.getInstance().signOut(this);
        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
//            default:
//                return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed-In", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign-In Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            } else if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {
                Uri imageUri = data.getData();

                //get the reference to stored file at database
                StorageReference photoReference = mChatPhotosReference.child(imageUri.getLastPathSegment());

                //upload file to firebase
                photoReference.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        FriendlyMessage message = new FriendlyMessage(null, mUsername, downloadUrl.toString());
                        mFirebaseReference.push().setValue(message);
                    }
                });
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener !=null){
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
        detachDatabaseReadListener();
        mMessageAdapter.clear();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    protected void OnSignedIn(String userName) {
        mUsername = userName;
        attachDatabaseReadListener();
    }

    protected void OnSignedOut() {
        mUsername = "Anonymous";
        mMessageAdapter.clear();
        detachDatabaseReadListener();
    }


    protected void attachDatabaseReadListener() {

        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
                    mMessageAdapter.add(friendlyMessage);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };

            mFirebaseReference.addChildEventListener(mChildEventListener);
        }

    }

    protected void detachDatabaseReadListener() {

        if (mChildEventListener !=null) {
            mFirebaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }

    }

    public void fetchConfig() {
        long cacheExpiration = 3600;
        if (mRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        mRemoteConfig.fetch(cacheExpiration).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mRemoteConfig.activateFetched();
                applyRetrievedLength();;


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error fetching Config", e);
                applyRetrievedLength();
            }
        });
    }

    private void applyRetrievedLength() {
        Long message_length = mRemoteConfig.getLong(MESSAGE_LENGTH_KEY);
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(message_length.intValue())});
        Log.d(TAG, MESSAGE_LENGTH_KEY + " = " + message_length);
    }
}
