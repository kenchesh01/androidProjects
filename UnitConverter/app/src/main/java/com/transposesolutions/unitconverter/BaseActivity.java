package com.transposesolutions.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

public class BaseActivity extends AppCompatActivity {
    private final static int THEME_BLUE = 1;
    private final static int THEME_GREEN = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateTheme();
    }
    public void updateTheme() {
        if (Utility.getTheme(getApplicationContext()) <= THEME_GREEN) {
            setTheme(R.style.Theme_green);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(R.color.dark_green));
            }
        } else if (Utility.getTheme(getApplicationContext()) == THEME_BLUE) {
            setTheme(R.style.AppTheme_Blue);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(R.color.primaryColorDark_blue));
            }
        }
    }
}