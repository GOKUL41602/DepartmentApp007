package com.example.loginpage.faculty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.loginpage.R;

public class SemesterDivisionPage extends AppCompatActivity {

    private Toolbar toolbar;

    private ImageView backBtn;

    private TextView toolbarTitleText;

    private RelativeLayout semDivisionRelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_division_page);
        initializeViews();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SemesterDivisionPage.this, FacultyHomePage.class);
                startActivity(intent);
                SemesterDivisionPage.this.finish();
            }
        });
    }

    private void initializeViews() {
        //semesterToolbar views
        toolbar = findViewById(R.id.semesterDivisionToolBar);
        backBtn = findViewById(R.id.semesterDivisionBackBtn);
        toolbarTitleText = findViewById(R.id.semesterDivisionToolBarTitleText);

        //semesterDivision relativeLayout
        semDivisionRelLayout = findViewById(R.id.semesterDivisionRelLayout);

    }
}