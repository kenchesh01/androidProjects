package com.transposesolutions.justnumbers;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MyAppSettings extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;
    Switch switchButton;
    Button mOption1, mOption2, mOption3, mOption4;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_app_settings);

        // Load Navigation View.
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // app bar background color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        // status bar background color
        Window window = MyAppSettings.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.BLACK);

        // XML items
        switchButton =  findViewById(R.id.switch1);
        mOption1 = findViewById(R.id.option_1);
        mOption2 = findViewById(R.id.option_2);
        mOption3 = findViewById(R.id.option_3);
        mOption4 = findViewById(R.id.option_4);
        // Database
        AppTracker appTracker = (AppTracker) getApplicationContext();
        boolean mClock = appTracker.getClockSettings();
        if (mClock) {
            switchButton.setChecked(true);
        }
        //set the switch to ON
        //attach a listener to check for changes in state
        switchButton.setOnCheckedChangeListener((buttonView, isChecked) -> {

            AppTracker appTracker1 = (AppTracker) getApplicationContext();
            if (isChecked) {
                appTracker1.setClockSettings(true);
                Toast.makeText(getApplicationContext(), "Timer is in ON State", Toast.LENGTH_LONG).show();
            } else {
                appTracker1.setClockSettings(false);
                Toast.makeText(getApplicationContext(), "Timer is in OFF State", Toast.LENGTH_LONG).show();
            }
        });

        // Called when the user taps addition button
        mOption1.setOnClickListener(view -> {
            //appTracker.setNumberOfQuestions(20);
            appTracker.setTotalMathProblems(1);
            Toast.makeText(getApplicationContext(), " 20 math problems  ", Toast.LENGTH_SHORT).show();
        });

        // Called when the user taps subtraction button
        mOption2.setOnClickListener(view -> {
            //  appTracker.setNumberOfQuestions(50);
            appTracker.setTotalMathProblems(2);
            Toast.makeText(getApplicationContext(), " 50 math problems  ", Toast.LENGTH_SHORT).show();
        });

        // Called when the user taps multiplication button
        mOption3.setOnClickListener(view -> {
            //   appTracker.setNumberOfQuestions(75);
            appTracker.setTotalMathProblems(3);
            Toast.makeText(getApplicationContext(), " 75 math problems  ", Toast.LENGTH_SHORT).show();
        });

        // Called when the user taps division button
        mOption4.setOnClickListener(view -> {
            //  appTracker.setNumberOfQuestions(100);
            appTracker.setTotalMathProblems(4);
            Toast.makeText(getApplicationContext(), " 100 math problems  ", Toast.LENGTH_SHORT).show();
        });

    }// end of onCreate


    // override method to responsible for responding correctly to the items specified in the menu resource file.
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // override method to listen for any click events on selecting a particular item from the drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // AppTracker Class to store/retrieve data
        AppTracker appTracker = (AppTracker)getApplicationContext();
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.main_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.addition) {
            Intent intent = new Intent(this, Addition.class);
            startActivity(intent);
        } else if (id == R.id.subtraction) {
            Intent intent = new Intent(this, Subtraction.class);
            startActivity(intent);
        } else if (id == R.id.multiplication) {
            Intent intent = new Intent(this, Multiplication.class);
            startActivity(intent);
        } else if (id == R.id.division) {
            Intent intent = new Intent(this, Division.class);
            startActivity(intent);
        } else if (id == R.id.Settings) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, MyAppSettings.class);
            startActivity(intent);
        } else if (id == R.id.nav_rate) {
            try {
                Uri uri1 = Uri.parse("market://details?id=" + getPackageName());
                Intent gotoMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket1);
            } catch (ActivityNotFoundException e){
                Uri uri1 = Uri.parse("http://play.google.com/store/apps/details/id=" + getPackageName());
                Intent gotoMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket1);
            }
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Check out this fun math game: Just Numbers! Math game to practice addition, subtraction, multiplication or division." +  "\n"+  "\n"
                    + "Google Play store:" +  "\n" +"https://play.google.com/store/apps/details?id=com.transposesolutions.justnumbers&hl=en" + "\n";
            String shareSub = "Check out this fun math game: Just Numbers from Transpose Solutions";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        } else if (id == R.id.nav_apps) {
            try {
                Uri uri1 = Uri.parse("https://play.google.com/store/apps/dev?id=8903808498078108637&hl=en");
                Intent gotoMarket2 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket2);
            } catch (ActivityNotFoundException e) {
                Uri uri1 = Uri.parse("https://play.google.com/store/apps/dev?id=8903808498078108637&hl=en");
                Intent gotoMarket2 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket2);
            }

        }
        return true;
    }

    // called when the user tapped device back button override it with onBackPressed() to ask if they want exit the app or stay?
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyAppSettings.this);
        builder.setMessage("Settings Updated ");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(MyAppSettings.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

