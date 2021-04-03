package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpage.faculty.FacultyHomePage;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    private Button facultyBtn, studentBtn, facultyLoginBtn, studentLoginBtn;

    private TextView facultyRegisterText, studentRegisterText;

    private String facultyUserNameText, facultyPasswordText, studentUserNameText, studentPasswordText, registerKey = "null";

    private TextInputLayout facultyUserName, facultyPassword, studentUserName, studentPassword;

    private RelativeLayout facultyRelLayout, studentRelLayout;

    private FirebaseDatabase database;

    private DatabaseReference reference;

    private RelativeLayout relativeLayout;

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

        facultyLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFacultyStrings();

            }
        });

        facultyRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, RegisterPage.class);
                registerKey = "faculty";
                intent.putExtra("registerKey", registerKey);
                startActivity(intent);
            }
        });

        studentRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, RegisterPage.class);
                registerKey = "student";
                intent.putExtra("registerKey", registerKey);
                startActivity(intent);
            }
        });

        facultyLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFacultyStrings();
                if (validateFacultyUserName()) {
                    if (validateFacultyPassword()) {
                        verifyFacultyLoginDetails();
                    } else {
                        validateFacultyPassword();
                    }
                } else {
                    validateFacultyUserName();
                }
            }
        });

        studentLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeStudentStrings();
                if (validateStudentUserName()) {
                    if (validateStudentPassword()) {
                        verifyStudentLoginDetails();
                    } else {
                        validateStudentPassword();
                    }
                } else {
                    validateStudentUserName();
                }
            }
        });

    }

    private void verifyStudentLoginDetails() {
        reference = database.getInstance().getReference("StudentsDto");
        Query query = reference.orderByChild("studentRollNo").startAt(studentUserNameText).endAt(studentUserNameText + "\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    studentUserName.setError(null);
                    studentUserName.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(studentUserNameText).child("studentPassword").getValue(String.class);
                    if (studentPasswordText.equals(passwordFromDB)) {
                        studentPassword.setError(null);
                        studentPassword.setErrorEnabled(false);
                        Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginPage.this, StudentNavigationDrawer.class);
                        startActivity(intent);
                        LoginPage.this.finish();
                    } else {
                        studentPassword.setError("Incorrect Password");
                        studentPassword.requestFocus();
                    }
                } else {
                    studentUserName.setError("UerName doesn't exists");
                    studentUserName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void verifyFacultyLoginDetails() {
        reference = database.getInstance().getReference("Faculty'sDto");
        Query query = reference.orderByChild("facultyRollNo").startAt(facultyUserNameText).endAt(facultyUserNameText + "\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    facultyUserName.setError(null);
                    facultyUserName.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(facultyUserNameText).child("facultyPassword").getValue(String.class);
                    if (facultyPasswordText.equals(passwordFromDB)) {
                        facultyPassword.setError(null);
                        facultyPassword.setErrorEnabled(false);
                        Intent intent = new Intent(LoginPage.this, FacultyHomePage.class);
                        startActivity(intent);
                    } else {
                        facultyPassword.setError("Incorrect Password");
                        facultyPassword.requestFocus();
                    }
                } else {
                    facultyUserName.setError("UerName doesn't exists");
                    facultyUserName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private boolean validateFacultyUserName() {
        if (facultyUserNameText.length() != 8) {
            facultyUserName.setError("Please Enter Valid UserName");
            facultyUserName.requestFocus();
            return false;
        } else {
            facultyUserName.setError(null);
            facultyUserName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyPassword() {
        if (facultyPasswordText.length() != 6) {
            facultyPassword.setError("Please Enter Valid Password");
            facultyPassword.requestFocus();
            return false;
        } else {
            facultyPassword.setError(null);
            facultyPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentUserName() {
        if (studentUserNameText.length() != 8) {
            studentUserName.setError("Please Enter Valid UserName");
            studentUserName.requestFocus();
            return false;
        } else {
            studentUserName.setError(null);
            studentUserName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentPassword() {
        if (studentPasswordText.length() != 6) {
            studentPassword.setError("Please Enter Valid Password");
            studentPassword.requestFocus();
            return false;
        } else {
            studentPassword.setError(null);
            studentPassword.setErrorEnabled(false);
            return true;
        }
    }

    private void initializeFacultyStrings() {
        facultyUserNameText = facultyUserName.getEditText().getText().toString();
        facultyPasswordText = facultyPassword.getEditText().getText().toString();
    }

    private void initializeStudentStrings() {
        studentUserNameText = studentUserName.getEditText().getText().toString();
        studentPasswordText = studentPassword.getEditText().getText().toString();
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

        relativeLayout = findViewById(R.id.registerPageRelLayout);
        facultyRelLayout = findViewById(R.id.loginPage_facultyRelLayout);
        studentRelLayout = findViewById(R.id.loginPage_studentRelLayout);


    }
}