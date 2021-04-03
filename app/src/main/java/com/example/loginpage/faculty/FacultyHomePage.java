package com.example.loginpage.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.loginpage.R;
import com.google.android.material.navigation.NavigationView;

public class FacultyHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private Toolbar toolbar;

    private NavigationView navigationView;

    private RelativeLayout noEmpInternRelLayout, empInternRelLayout, semRelLayout;

    private RecyclerView internRecView;

    private Button firstSemBtn, secondSemBtn, thirdSemBtn, fourthSemBtn, fifthSemBtn, sixthSemBtn, seventhSemBtn, eighthSemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home_page);
        initializeViews();

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.syncState();

        firstSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSemDivisionActivity();
            }
        });

        secondSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSemDivisionActivity();
            }
        });

        thirdSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSemDivisionActivity();
            }
        });
        fourthSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSemDivisionActivity();
            }
        });
        fifthSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSemDivisionActivity();
            }
        });
        sixthSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSemDivisionActivity();
            }
        });
        seventhSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSemDivisionActivity();
            }
        });
        eighthSemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSemDivisionActivity();
            }
        });
    }

    private void navigateToSemDivisionActivity() {
        Intent intent = new Intent(FacultyHomePage.this, SemesterDivisionPage.class);
        startActivity(intent);
    }

    private void initializeViews() {
        // drawerLayout and its contents in facultyHomePage
        drawerLayout = findViewById(R.id.facultyHomePage_drawerLayout);
        toolbar = findViewById(R.id.facultyHomePage_toolbar);
        navigationView = findViewById(R.id.facultyHomePage_nav_view);

        // relative layouts in faculty home page
        empInternRelLayout = findViewById(R.id.facultyHomePage_empRelLayout);
        noEmpInternRelLayout = findViewById(R.id.facultyHomePage_noEmpRelLayout);
        internRecView = findViewById(R.id.facultyHomePage_noEmpRelLayoutRecView);
        semRelLayout = findViewById(R.id.facultyHomePage_booksQuestions_semRelLayout);

        //recyclerView in facultyHomePage-Internship
        internRecView = findViewById(R.id.facultyHomePage_noEmpRelLayoutRecView);

        //buttons in semRelLayout
        firstSemBtn = findViewById(R.id.facultyHomePage_booksQuestionsRelLayout_1stSemBtn);
        secondSemBtn = findViewById(R.id.facultyHomePage_booksQuestionsRelLayout_2ndSemBtn);
        thirdSemBtn = findViewById(R.id.facultyHomePage_booksQuestionsRelLayout_3rdSemBtn);
        fourthSemBtn = findViewById(R.id.facultyHomePage_booksQuestionsRelLayout_4thSemBtn);
        fifthSemBtn = findViewById(R.id.facultyHomePage_booksQuestionsRelLayout_5thSemBtn);
        sixthSemBtn = findViewById(R.id.facultyHomePage_booksQuestionsRelLayout_6thSemBtn);
        seventhSemBtn = findViewById(R.id.facultyHomePage_booksQuestionsRelLayout_7thSemBtn);
        eighthSemBtn = findViewById(R.id.facultyHomePage_booksQuestionsRelLayout_8thSemBtn);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        FacultyHomePage.this.finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.faculty_nav_home:
                Intent intent = new Intent(FacultyHomePage.this, FacultyHomePage.class);
                startActivity(intent);
                FacultyHomePage.this.finish();

            case R.id.faculty_nav_questionPapers:
                empInternRelLayout.setVisibility(View.GONE);
                toolbar.setTitle("Questions & Resources");
                noEmpInternRelLayout.setVisibility(View.GONE);
                semRelLayout.setVisibility(View.VISIBLE);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}