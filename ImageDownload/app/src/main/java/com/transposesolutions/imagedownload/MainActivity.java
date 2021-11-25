package com.transposesolutions.imagedownload;

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

    public void OnUrl(View view) {
        Intent intent = new Intent(this,ImageUrl.class);
        startActivity(intent);
    }

    public void OnAbhi(View view) {
        Intent intent = new Intent(this,ImageAbhi.class);
        startActivity(intent);
    }

    public void OnTPoint(View view) {
        Intent intent = new Intent(this,TestImage.class);
        startActivity(intent);
    }
}