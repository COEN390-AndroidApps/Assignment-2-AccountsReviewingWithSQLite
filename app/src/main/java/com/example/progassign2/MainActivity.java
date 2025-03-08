package com.example.progassign2;

import com.example.progassign2.database.AppDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.progassign2.database.AppDatabase;
import com.example.progassign2.database.dao.StudentsDao;
import com.example.progassign2.database.entities.Students;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.progassign2.DialogBox;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    boolean states=false;
    private LinearLayout container;

    private Toolbar toolbar;
    private TextView title;
    private FloatingActionButton addProfile;
    private MenuItem item1;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUp();
        setOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        display(states);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; // This ensures the menu is displayed
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_clear)
        {
            db.studentsDao().clearAll();
            display(states);

        } else if (id==R.id.action_Sort) {
            states = !states;
            display(states);

        }
        return super.onOptionsItemSelected(item);
    }

    private void setUp()
    {
        startDataBase();
        setUpUi();
    }
    private void setUpUi(){
        setUpToolbar();
        title=findViewById(R.id.titleProfile);
        addProfile= findViewById(R.id.addProfile);
        container=findViewById(R.id.scroll);
        String num = String.valueOf(db.studentsDao().getAll().size());
        title.setText(num+" profiles");
    }

    private void setUpToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setOnClickListeners() {
        addProfile.setOnClickListener(v -> {
            DialogBox dialog = new DialogBox();
            dialog.show(getSupportFragmentManager(), "DialogBox");
        });
    }

    private void startDataBase() {
        db = AppDatabase.getInstance(getApplicationContext());
    }

    void updateProfileCount() {
        if (db != null) {
            int count = db.studentsDao().getAll().size();
            if(!states)
            {
                title.setText(count + " profiles by alphabetic order");
            }
            else
            {
                title.setText(count + " profiles by numeric order");
            }

        }
    }

    void display(boolean mode){
        container.removeAllViews();
        updateProfileCount();
        List<Students> list;
        if(!mode)
        {
            list = db.studentsDao().getAllSortedByName();
        }
        else {
            list = db.studentsDao().getAllSortedById();

        }

        for (int i = 0; i < list.size(); i++) {
            Students student = list.get(i);
            final int index = i;
            TextView studentTextView = new TextView(this);
            studentTextView.setText(student.getName() + " " + student.getStudentSurname());

            studentTextView.setTextSize(18);
            studentTextView.setPadding(10, 10, 10, 10);
            studentTextView.setClickable(true);


            studentTextView.setOnClickListener(v -> {
                // Open ProfileActivity and pass student details
                Intent intent = new Intent(MainActivity.this, profileActivity.class);
                intent.putExtra("student_id", list.get(index).student_id);
                intent.putExtra("student_name", list.get(index).getName());
                intent.putExtra("student_surname", list.get(index).getStudentSurname());
                intent.putExtra("student_gpa", list.get(index).gpa);
                startActivity(intent);
            });

            container.addView(studentTextView);
        }
    }

}