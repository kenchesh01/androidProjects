package com.transposesolutions.themeconcept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.check_theme_btn)).setOnClickListener(this);
        ((Button)findViewById(R.id.theme_btn)).setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_theme_btn:
                startActivity(new Intent(this,TestActivity.class));
                break;
            case R.id.theme_btn:
                startActivity(new Intent(this,ThemeActivity.class));
                break;

        }
    }
    public void recreateActivity() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_tool,menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){

        // toolbar onOptionItemSelected
        if (item.getItemId() == R.id.lightTheme) {
            lightThemeUpdate();  return true;
        }else if
        (item.getItemId() == R.id.blueTheme) {
            blueThemeUpdate();  return true;
        }else if
        (item.getItemId() == R.id.redTheme) {
            redThemeUpdate();  return true;
        }else if
        (item.getItemId() == R.id.greenTheme) {
            greenThemeUpdate();  return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void lightThemeUpdate() {
        Toast.makeText(MainActivity.this, "Light theme", Toast.LENGTH_SHORT).show();


    }
    private void blueThemeUpdate() {
        Utility.setTheme(getApplicationContext(), 1);
        recreateActivity();

    }
    private void redThemeUpdate() {
        Utility.setTheme(getApplicationContext(), 2);
        recreateActivity();

    }
    private void greenThemeUpdate() {

        Utility.setTheme(getApplicationContext(), 3);
        recreateActivity();
    }


}