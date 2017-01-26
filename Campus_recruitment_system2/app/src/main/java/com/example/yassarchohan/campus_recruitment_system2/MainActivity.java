package com.example.yassarchohan.campus_recruitment_system2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn,btn1,btn2;
    public SharedPreferences iterate;
    public int select = 0;
    public SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.signinForAdmin);
        btn1 = (Button) findViewById(R.id.signinForCompany);
        btn2 = (Button) findViewById(R.id.signinForStudent);



        iterate = getSharedPreferences("label", 0);
        select = iterate.getInt("tag",0);


        //To edit the variables and commit (store) them:
        mEditor = iterate.edit();

        if (select == 1)
        {
            Intent intent = new Intent(MainActivity.this,Sign_in_Admin.class);
            startActivity(intent);
        }else if(select == 2)
        {
            Intent intent = new Intent(MainActivity.this,Sign_in_Company.class);
            startActivity(intent);
        }else if(select == 3)
        {
            Intent intent = new Intent(MainActivity.this,Sign_in_student.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Please Login!", Toast.LENGTH_SHORT).show();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putInt("tag",1).commit();
                Intent intent = new Intent(MainActivity.this,Sign_in_Admin.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putInt("tag",2).commit();
                Intent intent = new Intent(MainActivity.this,Sign_in_Company.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putInt("tag",3).commit();
                Intent intent = new Intent(MainActivity.this,Sign_in_student.class);
                startActivity(intent);
            }
        });
    }
}
