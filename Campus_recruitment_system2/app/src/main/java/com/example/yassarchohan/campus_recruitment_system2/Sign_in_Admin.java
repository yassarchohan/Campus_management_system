package com.example.yassarchohan.campus_recruitment_system2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Sign_in_Admin extends AppCompatActivity {

    private String mUsername;
    public static final int RC_SIGN_IN = 1;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    private DatabaseReference Mreferences;
    private ChildEventListener mchildeventlistener;
    private Getter_methods gm;
    private Custom_adapter_student customAdapter;
    private  ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in__admin);



         ListView list = (ListView) findViewById(R.id.List);
        final List<Getter_methods> messages = new ArrayList<>();
        customAdapter = new Custom_adapter_student(this, R.layout.activity_custom_view, messages);
        list.setAdapter(customAdapter);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();


        Mreferences = mFirebaseDatabase.getReference().child("Posts");

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


        mchildeventlistener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                gm = dataSnapshot.getValue(Getter_methods.class);
                gm.setNodeKey(dataSnapshot.getKey());
                customAdapter.notifyDataSetChanged();
                customAdapter.add(gm);
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
        Mreferences.addChildEventListener(mchildeventlistener);


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



}




