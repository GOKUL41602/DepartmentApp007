package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button facultyBtn, studentBtn, facultyLoginBtn, studentLoginBtn;

    private TextView facultyRegisterText, studentRegisterText;

    private String registerKey = "null";

    private TextInputLayout facultyUserName, facultyPassword, studentUserName, studentPassword;

    private RelativeLayout facultyRelLayout, studentRelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeFacultyRelViews();
        initializeStudentRelViews();
        facultyBtn.setPaintFlags(facultyBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        facultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facultyBtn.setPaintFlags(facultyBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                studentBtn.setPaintFlags(0);
                facultyRelLayout.setVisibility(View.VISIBLE);
                studentRelLayout.setVisibility(View.GONE);
            }
        });


        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facultyBtn.setPaintFlags(0);
                studentBtn.setPaintFlags(facultyBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                facultyRelLayout.setVisibility(View.GONE);
                studentRelLayout.setVisibility(View.VISIBLE);
            }
        });

        facultyRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterPage.class);
                registerKey = "faculty";
                intent.putExtra("registerKey", registerKey);
                startActivity(intent);
            }
        });

        studentRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterPage.class);
                registerKey = "student";
                intent.putExtra("registerKey", registerKey);
                startActivity(intent);
            }
        });

    }

    private void initializeFacultyRelViews() {
        facultyUserName = findViewById(R.id.loginPage_facultyUserName);
        facultyPassword = findViewById(R.id.loginPage_facultyPassword);
        facultyRegisterText = findViewById(R.id.loginPage_facultyRegisterText);
        facultyLoginBtn = findViewById(R.id.loginPage_facultyLoginBtn);
    }

    private void initializeStudentRelViews() {
        studentUserName = findViewById(R.id.loginPage_studentUserName);
        studentPassword = findViewById(R.id.loginPage_studentPassword);
        studentRegisterText = findViewById(R.id.loginPage_studentRegisterText);
        studentLoginBtn = findViewById(R.id.loginPage_studentLoginBtn);
    }

    private void initializeViews() {

        facultyBtn = findViewById(R.id.loginPage_facultyBtn);
        studentBtn = findViewById(R.id.loginPage_studentBtn);

        facultyRelLayout = findViewById(R.id.loginPage_facultyRelLayout);
        studentRelLayout = findViewById(R.id.loginPage_studentRelLayout);


    }
}