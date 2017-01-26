package com.example.yassarchohan.campus_recruitment_system2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_in_student extends AppCompatActivity implements Student_post.callbackmethod {

    private String mUsername;
    public static final int RC_SIGN_IN = 1;

    private FirebaseDatabase mFirebaseDatabase;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    Button btn,btn1,btn2;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_student);

        btn = (Button) findViewById(R.id.postingstudent);
        btn1 = (Button) findViewById(R.id.listofjobs);
        btn2 = (Button) findViewById(R.id.listofstudents);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fragment1, new Student_post()).commit();
                Toast.makeText(Sign_in_student.this, "Post all your details here", Toast.LENGTH_SHORT).show();

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
//                    Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
                    OnSignedInInitialized(user.getDisplayName());

                } else {
                    OnSignedOutCleanUp();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(
                                            AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);

                }

            }
        };
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {

                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }


        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAuthStateListner != null) {
            mFirebaseAuth.addAuthStateListener(mAuthStateListner);
        }
    }


    private void OnSignedInInitialized(String displayName) {
        mUsername = displayName;
    }


    private void OnSignedOutCleanUp() {
        mUsername = "ANONYMOUS";
    }


    @Override
    public void messagetoActivity(String message) {

    }
}

