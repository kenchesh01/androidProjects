package com.transposesolutions.loginassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //public static final String SEND_MESSAGE1 = "com.transposesolutions.loginassignment.First_Name";
    private ActionBarDrawerToggle mToggle;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);



        b1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent =new Intent(MainActivity.this,BasicLogin.class);startActivity(intent); }});
       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent(MainActivity.this,LocalLogin.class);startActivity(intent); }});
       b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AppTracker validate = (AppTracker)getApplicationContext();
               boolean checkLogIn =validate.getButtonCheck();
               if(checkLogIn) {
                   startActivity(new Intent(MainActivity.this,CloudLogin.class));
               }else{
                   startActivity(new Intent(MainActivity.this,DisplayActivity.class));
               }
           }});

       // Initialize Mobile Ads SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        /* Load an ad into the AdMob banner view.*/
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        /*navigation drawerlayout */
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.basic_login) {
            Intent intent = new Intent(this, BasicLogin.class);
            startActivity(intent);
        } else if (id == R.id.local_login) {
            Intent intent = new Intent(this, LocalLogin.class);
            startActivity(intent);
        } else if (id ==R.id.cloud_login){
            Intent intent =new Intent (this,CloudLogin.class);
            startActivity(intent);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}