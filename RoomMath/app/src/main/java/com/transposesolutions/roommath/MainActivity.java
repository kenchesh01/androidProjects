package com.transposesolutions.roommath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view) {
        Intent intent= new Intent(this,Addition1.class);
        startActivity(intent);
    }

    public void second(View view) {
        Intent intent= new Intent(this,Addition2.class);
        startActivity(intent);
    }
}