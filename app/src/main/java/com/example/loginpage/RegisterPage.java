package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity {

    private ScrollView facultyScrollView, studentScrollView;

    private RelativeLayout relativeLayout;

    private TextInputLayout facultyName, facultyRollNo, facultyPhoneNo, facultyDesignation, facultySubjectOfInterest, facultyExperience, facultyAddress, facultyEducation, facultyGmail, facultyCreatePassword, facultyConfirmPassword,
            studentName, studentRollNo, studentPhoneNo, studentCurrentYear, studentAddress, studentGmail, studentCreatePassword, studentConfirmPassword;

    private String facultyNameText, facultyRollNoText, facultyPhoneNoText, facultyDesignationText, facultySubjectOfInterestText, facultyExperienceText, facultyAddressText, facultyEducationText, facultyGmailText, facultyCreatePasswordText, facultyConfirmPasswordText,
            studentNameText, studentRollNoText, studentPhoneNoText, studentCurrentYearText, studentAddressText, studentGmailText, studentCreatePasswordText, studentConfirmPasswordText, genderText = "null";

    private RadioGroup facultyGender, studentGender;

    private RadioButton facultyRadioBtn, studentRadioBtn;

    private Button facultyRegisterBtn, facultyCancelBtn, studentRegisterBtn, studentCancelBtn;

    private String registerKey = "null";

    private DatabaseReference reference;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        registerKey = getIntent().getStringExtra("registerKey");
        initializeViews();
        if (registerKey.equals("faculty")) {
            facultyScrollView.setVisibility(View.VISIBLE);
            studentScrollView.setVisibility(View.GONE);
            initializeFacultyRelLayoutViews();
        } else if (registerKey.equals("student")) {
            studentScrollView.setVisibility(View.VISIBLE);
            facultyScrollView.setVisibility(View.GONE);
            initializeStudentRelLayoutViews();
        }

        facultyRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeFacultyStrings();
                if (validateFacultyName()) {
                    if (validateFacultyRollNo()) {
                        if (validateFacultyPhoneNo()) {
                            if (validateFacultyDesignation()) {
                                if (validateFacultySubjectOfInterest()) {
                                    if (validateFacultyExperience()) {
                                        if (validateFacultyAddress()) {
                                            if (validateFacultyEducation()) {
                                                if (validateFacultyGmail()) {
                                                    if (validateFacultyCreatePassword()) {
                                                        if (validateFacultyConfirmPassword()) {
                                                            if (validateFacultyPassword()) {
                                                                facultyGender();
                                                            } else {
                                                                validateFacultyPassword();
                                                            }
                                                        } else {
                                                            validateFacultyConfirmPassword();
                                                        }
                                                    } else {
                                                        validateFacultyCreatePassword();
                                                    }

                                                } else {
                                                    validateFacultyGmail();
                                                }
                                            } else {
                                                validateFacultyEducation();
                                            }
                                        } else {
                                            validateFacultyAddress();
                                        }
                                    } else {
                                        validateFacultyExperience();
                                    }
                                } else {
                                    validateFacultySubjectOfInterest();
                                }
                            } else {
                                validateFacultyDesignation();
                            }
                        } else {
                            validateFacultyPhoneNo();
                        }
                    } else {
                        validateFacultyRollNo();
                    }
                } else {
                    validateFacultyName();
                }
            }
        });

        studentRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeStudentStrings();
                if (validateStudentName()) {
                    if (validateStudentRollNo()) {
                        if (validateStudentPhoneNo()) {
                            if (validateStudentCurrentYear()) {
                                if (validateStudentGmail()) {
                                    if (validateStudentAddress()) {
                                        if (validateStudentCreatePassword()) {
                                            if (validateStudentConfirmPassword()) {
                                                if (validateStudentPassword()) {
                                                    studentGender();
                                                } else {
                                                    validateStudentPassword();
                                                }
                                            } else {
                                                validateStudentConfirmPassword();
                                            }
                                        } else {
                                            validateStudentCreatePassword();
                                        }
                                    } else {
                                        validateStudentAddress();
                                    }
                                } else {
                                    validateStudentGmail();
                                }
                            } else {
                                validateStudentPhoneNo();
                            }
                        } else {
                            validateStudentPhoneNo();
                        }
                    } else {
                        validateStudentRollNo();
                    }
                } else {
                    validateStudentName();
                }
            }
        });

        facultyCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
                RegisterPage.this.finish();
            }
        });

        studentCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
                RegisterPage.this.finish();
            }
        });
    }

    private void verifyFacultyRollNo() {
        reference = database.getInstance().getReference("Faculty'sDto");

        Query query = reference.orderByChild("facultyRollNo").startAt(facultyRollNoText).endAt(facultyRollNoText + "\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    facultyRollNo.setError("Roll No already exists");
                    facultyRollNo.requestFocus();
                } else {
                    facultyRollNo.setError(null);
                    facultyRollNo.setErrorEnabled(false);
                    uploadFacultyDataToDatabase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void verifyStudentRollNo() {
        reference = database.getInstance().getReference("StudentsDto");
        Query query = reference.orderByChild("studentRollNo").startAt(studentRollNoText).endAt(studentRollNoText + "\uf8ff");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    studentRollNo.setError("Roll No already exists");
                    studentRollNo.requestFocus();
                } else {
                    studentRollNo.setError(null);
                    studentRollNo.setErrorEnabled(false);
                    uploadStudentDataToDatabase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void facultyGender() {

        if (facultyGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        } else {
            int selectedId = facultyGender.getCheckedRadioButtonId();
            facultyRadioBtn = findViewById(selectedId);
            genderText = facultyRadioBtn.getText().toString();
            verifyFacultyRollNo();
        }
    }

    private void uploadFacultyDataToDatabase() {
        reference = database.getInstance().getReference("Faculty'sDto");
        FacultyDto facultyDto = new FacultyDto(facultyNameText, facultyRollNoText, facultyPhoneNoText, facultyDesignationText, facultySubjectOfInterestText, facultyExperienceText, facultyAddressText, facultyEducationText, genderText, facultyConfirmPasswordText);
        reference.child(facultyRollNoText).setValue(facultyDto);
        Snackbar.make(relativeLayout, "Registration Successful", Snackbar.LENGTH_INDEFINITE)
                .setAction("LOGIN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                        startActivity(intent);
                        RegisterPage.this.finish();
                    }
                }).show();
    }

    private void studentGender() {

        if (studentGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
        } else {
            int selectedId = studentGender.getCheckedRadioButtonId();
            studentRadioBtn = findViewById(selectedId);
            genderText = studentRadioBtn.getText().toString();
            verifyStudentRollNo();
        }

    }

    private void uploadStudentDataToDatabase() {
        reference = database.getInstance().getReference("StudentsDto");
        StudentsDto studentsDto = new StudentsDto(studentNameText, studentRollNoText, studentPhoneNoText, studentCurrentYearText, studentGmailText, studentAddressText, genderText, studentConfirmPasswordText);
        reference.child(studentRollNoText).setValue(studentsDto);

        Snackbar.make(relativeLayout, "Registration Successful", Snackbar.LENGTH_INDEFINITE)
                .setAction("LOGIN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                        startActivity(intent);
                        RegisterPage.this.finish();
                    }
                }).show();
    }


    private void initializeFacultyStrings() {

        facultyNameText = facultyName.getEditText().getText().toString();
        facultyRollNoText = facultyRollNo.getEditText().getText().toString();
        facultyPhoneNoText = facultyPhoneNo.getEditText().getText().toString();
        facultyDesignationText = facultyDesignation.getEditText().getText().toString();
        facultySubjectOfInterestText = facultySubjectOfInterest.getEditText().getText().toString();
        facultyExperienceText = facultyExperience.getEditText().getText().toString();
        facultyAddressText = facultyAddress.getEditText().getText().toString();
        facultyEducationText = facultyEducation.getEditText().getText().toString();
        facultyGmailText = facultyGmail.getEditText().getText().toString();
        facultyCreatePasswordText = facultyCreatePassword.getEditText().getText().toString();
        facultyConfirmPasswordText = facultyConfirmPassword.getEditText().getText().toString();

    }

    private boolean validateFacultyName() {
        if (facultyNameText.length() <= 2 || facultyNameText.isEmpty()) {
            facultyName.setError("Please Enter Name");
            facultyName.requestFocus();
            return false;
        } else {
            facultyName.setError(null);
            facultyName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyRollNo() {
        if (facultyRollNoText.length() != 8) {
            facultyRollNo.setError("Please Enter Roll No");
            facultyRollNo.requestFocus();
            return false;
        } else {
            facultyRollNo.setError(null);
            facultyRollNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyPhoneNo() {
        if (facultyPhoneNoText.length() != 10) {
            facultyPhoneNo.setError("Please Enter Valid Phone Number");
            facultyPhoneNo.requestFocus();
            return false;
        } else {
            facultyPhoneNo.setError(null);
            facultyPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyDesignation() {
        if (facultyDesignationText.length() <= 4 || facultyDesignationText.isEmpty()) {
            facultyDesignation.setError("Please Enter Designation");
            facultyDesignation.requestFocus();
            return false;
        } else {
            facultyDesignation.setError(null);
            facultyDesignation.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultySubjectOfInterest() {
        if (facultySubjectOfInterestText.length() <= 4 || facultySubjectOfInterestText.isEmpty()) {
            facultySubjectOfInterest.setError("Please Enter Subject of Interest");
            facultySubjectOfInterest.requestFocus();
            return false;
        } else {
            facultySubjectOfInterest.setError(null);
            facultySubjectOfInterest.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyExperience() {
        if (facultyExperienceText.isEmpty()) {
            facultyExperience.setError("Please Enter Experience");
            facultyExperience.requestFocus();
            return false;
        } else {
            facultyExperience.setError(null);
            facultyExperience.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyAddress() {
        if (facultyAddressText.isEmpty() || facultyAddressText.length() <= 3) {
            facultyAddress.setError("Please Enter Valid Address");
            facultyAddress.requestFocus();
            return false;
        } else {
            facultyAddress.setError(null);
            facultyAddress.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyEducation() {
        if (facultyEducationText.isEmpty() || facultyEducationText.length() <= 3) {
            facultyEducation.setError("Please Enter Valid Address");
            facultyEducation.requestFocus();
            return false;
        } else {
            facultyEducation.setError(null);
            facultyEducation.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyGmail() {
        if (facultyGmailText.isEmpty()) {
            facultyGmail.setError("Please Enter Valid E-Mail Address");
            facultyGmail.requestFocus();
            return false;
        } else {
            facultyGmail.setError(null);
            facultyGmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyCreatePassword() {
        if (facultyCreatePasswordText.length() < 6) {
            facultyCreatePassword.setError("Please Enter Valid 6 Digit Password");
            facultyCreatePassword.requestFocus();
            return false;
        } else {
            facultyCreatePassword.setError(null);
            facultyCreatePassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyConfirmPassword() {
        if (facultyConfirmPasswordText.length() < 6) {
            facultyConfirmPassword.setError("Please Enter Valid 6 Digit Password");
            facultyConfirmPassword.requestFocus();
            return false;
        } else {
            facultyConfirmPassword.setError(null);
            facultyConfirmPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateFacultyPassword() {
        if (facultyCreatePasswordText.equals(facultyConfirmPasswordText)) {

            facultyConfirmPassword.setError(null);
            facultyConfirmPassword.setErrorEnabled(false);
            return true;
        } else {
            facultyConfirmPassword.setError("Password doesn't match");
            facultyConfirmPassword.requestFocus();
            return false;
        }
    }

    private boolean validateStudentName() {
        if (studentNameText.length() <= 2 | studentNameText.isEmpty()) {
            studentName.setError("Please Enter Valid Name");
            studentName.requestFocus();
            return false;
        } else {
            studentName.setError(null);
            studentName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentRollNo() {
        if (studentRollNoText.length() != 8) {
            studentRollNo.setError("Please Enter Valid Roll No");
            studentRollNo.requestFocus();
            return false;
        } else {
            studentRollNo.setError(null);
            studentRollNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentPhoneNo() {
        if (studentPhoneNoText.length() != 10) {
            studentPhoneNo.setError("Please Enter Valid Phone Number");
            studentPhoneNo.requestFocus();
            return false;
        } else {
            studentPhoneNo.setError(null);
            studentPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentCurrentYear() {
        if (studentCurrentYearText.length() <= 2) {
            studentCurrentYear.setError("Please Enter Valid Current Year");
            studentCurrentYear.requestFocus();
            return false;
        } else {
            studentCurrentYear.setError(null);
            studentCurrentYear.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentGmail() {
        if (studentGmailText.length() <= 2) {
            studentGmail.setError("Please Enter Valid E-Mail Address");
            studentGmail.requestFocus();
            return false;
        } else {
            studentGmail.setError(null);
            studentGmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentAddress() {
        if (studentAddressText.length() <= 2) {
            studentAddress.setError("Please Enter Valid Address");
            studentAddress.requestFocus();
            return false;
        } else {
            studentAddress.setError(null);
            studentAddress.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentCreatePassword() {
        if (studentCreatePasswordText.length() < 6) {
            studentCreatePassword.setError("Please Enter Valid 6 Digit Password");
            studentCreatePassword.requestFocus();
            return false;
        } else {
            studentCreatePassword.setError(null);
            studentCreatePassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentConfirmPassword() {
        if (studentConfirmPasswordText.length() < 6) {
            studentConfirmPassword.setError("Please Enter Valid 6 Digit Password");
            studentConfirmPassword.requestFocus();
            return false;
        } else {
            studentConfirmPassword.setError(null);
            studentConfirmPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentPassword() {
        if (studentCreatePasswordText.equals(studentConfirmPasswordText)) {

            studentConfirmPassword.setError(null);
            studentConfirmPassword.setErrorEnabled(false);
            return true;
        } else {
            studentConfirmPassword.setError("Password doesn't match");
            studentConfirmPassword.requestFocus();
            return false;
        }
    }


    private void initializeStudentStrings() {

        studentNameText = studentName.getEditText().getText().toString();
        studentRollNoText = studentRollNo.getEditText().getText().toString();
        studentPhoneNoText = studentPhoneNo.getEditText().getText().toString();
        studentCurrentYearText = studentCurrentYear.getEditText().getText().toString();
        studentGmailText = studentGmail.getEditText().getText().toString();
        studentAddressText = studentAddress.getEditText().getText().toString();
        studentGender = findViewById(R.id.registerPage_studentGender);

        studentCreatePasswordText = studentCreatePassword.getEditText().getText().toString();
        studentConfirmPasswordText = studentConfirmPassword.getEditText().getText().toString();
    }

    private void initializeFacultyRelLayoutViews() {

        facultyName = findViewById(R.id.registerPage_facultyName);
        facultyRollNo = findViewById(R.id.registerPage_facultyRollNo);
        facultyPhoneNo = findViewById(R.id.registerPage_facultyPhoneNo);
        facultyDesignation = findViewById(R.id.registerPage_facultyDesignation);
        facultySubjectOfInterest = findViewById(R.id.registerPage_facultySubjectOfInterest);
        facultyExperience = findViewById(R.id.registerPage_facultyExperience);
        facultyAddress = findViewById(R.id.registerPage_facultyCity_Town);
        facultyEducation = findViewById(R.id.registerPage_facultyEducation);
        facultyGmail = findViewById(R.id.registerPage_facultyGmail);
        facultyGender = findViewById(R.id.registerPage_facultyGender);

        facultyCreatePassword = findViewById(R.id.registerPage_facultyCreatePassword);
        facultyConfirmPassword = findViewById(R.id.registerPage_facultyConfirmPassword);
    }

    private void initializeStudentRelLayoutViews() {

        studentName = findViewById(R.id.registerPage_studentName);
        studentRollNo = findViewById(R.id.registerPage_studentRollNo);
        studentPhoneNo = findViewById(R.id.registerPage_studentPhoneNo);
        studentCurrentYear = findViewById(R.id.registerPage_studentCurrentYear);
        studentGmail = findViewById(R.id.registerPage_studentGmail);
        studentAddress = findViewById(R.id.registerPage_studentCity_Town);

        studentCreatePassword = findViewById(R.id.registerPage_studentCreatePassword);
        studentConfirmPassword = findViewById(R.id.registerPage_studentConfirmPassword);

    }

    private void initializeViews() {
        facultyScrollView = findViewById(R.id.registerPage_facultyScrollView);
        studentScrollView = findViewById(R.id.registerPage_studentScrollView);
        facultyRegisterBtn = findViewById(R.id.registerPage_facultyRegisterBtn);
        studentRegisterBtn = findViewById(R.id.registerPage_studentRegisterBtn);
        facultyCancelBtn = findViewById(R.id.registerPage_facultyCancelBtn);
        studentCancelBtn = findViewById(R.id.registerPage_studentCancelBtn);

        relativeLayout = findViewById(R.id.registerPageRelLayout);
    }
}