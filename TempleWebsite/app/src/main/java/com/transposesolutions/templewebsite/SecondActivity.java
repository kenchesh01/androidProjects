package com.transposesolutions.templewebsite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // Initialize and assign values
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        // set home Activity
        bottomNavigationView.setSelectedItemId(R.id.navigation_second);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                    // Handle the camera action
                    Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                    startActivity(intent);}
                if (id == R.id.navigation_second) {
                    // Handle the camera action
                    Intent intent = new Intent(SecondActivity.this,SecondActivity.class);
                    startActivity(intent);}

                return false;
            }
        });
    }
}