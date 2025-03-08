package com.example.progassign2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class profileActivity extends AppCompatActivity {
    private TextView name,surname,gpa,id;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUp();
    }
    private void setUp(){

        setUpToolbar();
        setUpUi();
        getDisplayIntent();
    }

    private void setUpUi()
    {
        name=findViewById(R.id.textViewName);
        surname = findViewById(R.id.textViewSurname);
        gpa=findViewById(R.id.textViewGpa);
        id=findViewById(R.id.textViewId);
    }

    private void setUpToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }
    private void getDisplayIntent()
    {
        Intent intent = getIntent();

        if (intent != null) {
            int studentId = intent.getIntExtra("student_id", -1); // Default to -1 if not found
            String studentName = intent.getStringExtra("student_name");
            String studentSurname = intent.getStringExtra("student_surname");
            String studentGpa = intent.getStringExtra("student_gpa");

            // Display student details
            id.setText("ID: " + studentId);
            name.setText("Name: " + studentName);
            surname.setText("Surname: " + studentSurname);
            gpa.setText("GPA: " + studentGpa);
        }
    }
}