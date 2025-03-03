package com.example.progassign2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.progassign2.DialogBox;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView title;
    FloatingActionButton addProfile;
    
    MenuItem item1;



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
        setUpUi();
        setOnClickListeners();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; // This ensures the menu is displayed
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpUi(){
        setUpToolbar();
        title=findViewById(R.id.titleProfile);
        addProfile= findViewById(R.id.addProfile);
    }

    private void setUpToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setOnClickListeners(){
        View.OnClickListener event = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox dialog = new DialogBox();
                dialog.show(getSupportFragmentManager(), "DialogBox");
            }
        };
        addProfile.setOnClickListener(event);


    }
}