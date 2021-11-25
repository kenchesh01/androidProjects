package com.transposesolutions.themeconcept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class ThemeActivity extends BaseActivity {
    Button mOption1, mOption2, mOption3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        mOption1 = findViewById(R.id.option_1);
        mOption2 = findViewById(R.id.option_2);
        mOption3 = findViewById(R.id.option_3);
        // Called when the user taps Green theme button
        mOption1.setOnClickListener(view -> {
            Utility.setTheme(getApplicationContext(), 1);
            recreateActivity();
            Toast.makeText(getApplicationContext(), " Blue theme selected  ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ThemeActivity.this,MainActivity.class));
        });

        // Called when the user taps black theme  button
        mOption2.setOnClickListener(view -> {
            Utility.setTheme(getApplicationContext(), 2);
            recreateActivity();
            Toast.makeText(getApplicationContext(), " Red theme selected  ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ThemeActivity.this,MainActivity.class));

        });

        // Called when the user taps brow theme  button
        mOption3.setOnClickListener(view -> {
            Utility.setTheme(getApplicationContext(), 3);
            recreateActivity();
            Toast.makeText(getApplicationContext(), " Green theme selected  ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ThemeActivity.this,MainActivity.class));

        });

    }
    public void recreateActivity() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}