package com.example.yassarchohan.campus_recruitment_system2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Student_post extends Fragment {

    private EditText edt,edt1,edt2,edt3;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference Mreferences;
    private ChildEventListener mchildeventlistener;
    String name, course, job,gpa;
    private Getter_methods gm;
    public String currenttime;
    private NotificationCompat.Builder notification;
    Button btn;


    public Student_post() {
        // Required empty public constructor
    }
    public interface callbackmethod {

        public void messagetoActivity(String message);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


         View v = inflater.inflate(R.layout.fragment_student_post,container,false);


        edt = (EditText) v.findViewById(R.id.Studentname);
        edt1 = (EditText) v.findViewById(R.id.Studentcourse);
        edt2 = (EditText) v.findViewById(R.id.Studentjob);
        edt3 = (EditText) v.findViewById(R.id.Studentgpa);

        firebaseDatabase = FirebaseDatabase.getInstance();
        Mreferences = firebaseDatabase.getReference().child("Postsforstudents");

        notification = new NotificationCompat.Builder(getActivity());
        notification.setAutoCancel(true);



        btn = (Button) v.findViewById(R.id.poststudent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edt.getText().toString();
                course = edt1.getText().toString();
                job = edt2.getText().toString();
                gpa = edt3.getText().toString();
                String key = Mreferences.push().getKey();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy KK:mm");
//                currenttime = sdf.format(new Date());


                if (name == null || course == null || job == null || gpa == null) {

                    Toast.makeText(getActivity(), "could not submit post please enter all details", Toast.LENGTH_SHORT).show();

                } else {
                    gm = new Getter_methods(name, course, job, key, gpa);
                    Mreferences.push().setValue(gm);
                    Toast.makeText(getActivity(), "Posted Successfully Thank you", Toast.LENGTH_SHORT).show();

                    edt.setText("");
                    edt1.setText("");
                    edt2.setText("");

                }


            }

        });

//        mchildeventlistener = new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//
//        Mreferences.addChildEventListener(mchildeventlistener);

        return v;

    }



    }


