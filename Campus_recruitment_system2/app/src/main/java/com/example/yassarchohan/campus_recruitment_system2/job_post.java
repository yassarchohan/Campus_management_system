package com.example.yassarchohan.campus_recruitment_system2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class job_post extends AppCompatActivity {

    Button btn;
    EditText edt, edt1, edt2;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference Mreferences;
    private ChildEventListener mchildeventlistener;
    String name, posted, design;
    private Getter_methods gm;
    public String currenttime;
    private NotificationCompat.Builder notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);

        edt = (EditText) findViewById(R.id.com_name);
        edt1 = (EditText) findViewById(R.id.postedby);
        edt2 = (EditText) findViewById(R.id.designation);

        firebaseDatabase = FirebaseDatabase.getInstance();
        Mreferences = firebaseDatabase.getReference().child("Posts");

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);


        final Intent intent2 = new Intent(this,job_post.class);
        notification = new NotificationCompat.Builder(this);

        btn = (Button) findViewById(R.id.submitpost);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edt.getText().toString();
                posted = edt1.getText().toString();
                design = edt2.getText().toString();
                String key = Mreferences.push().getKey();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy KK:mm");
                currenttime = sdf.format(new Date());


                if (name == null || posted == null || design == null) {

                    Toast.makeText(job_post.this, "could not submit post please enter all details", Toast.LENGTH_SHORT).show();

                } else {
                    gm = new Getter_methods(name, posted, design, key, currenttime);
                    Mreferences.push().setValue(gm);
                    Toast.makeText(job_post.this, "Posted Successfully Thank you", Toast.LENGTH_SHORT).show();

                    edt.setText("");
                    edt1.setText("");
                    edt2.setText("");

                }


            }

        });

        mchildeventlistener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                notification.setAutoCancel(true);
                final PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pi);



                notification.setContentText("Checkout New Job is Posted");
                notification.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);
                notification.setContentTitle("Campus_recruitment system");
                notification.setTicker("Checkout New Job is Posted");


                notification.setWhen(System.currentTimeMillis());

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(0,notification.build());

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
}






