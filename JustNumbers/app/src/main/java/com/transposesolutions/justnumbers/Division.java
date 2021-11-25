package com.transposesolutions.justnumbers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class Division extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;
    private AdView adView;
    Button module_1, module_2, module_3, module_4, module_5, module_6, module_7, module_8;
    // database code
    public static MyAppDataBase myAppDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);
        //Database
        myAppDataBase =MyAppDataBase.getDbInstance(this.getApplicationContext());
// check the data table empty or not
        List<UserDataTable> usersData = myAppDataBase.myDao().getUsers1();
        int size = usersData.size();
        if(size>0){
            myAppDataBase.myDao().deleteAllData();
        }
        // Declare XML View
        module_1 = findViewById(R.id.module_1);
        module_2 = findViewById(R.id.module_2);
        module_3 = findViewById(R.id.module_3);
        module_4 = findViewById(R.id.module_4);
        module_5 = findViewById(R.id.module_5);
        module_6 = findViewById(R.id.module_6);
        module_7 = findViewById(R.id.module_7);
        module_8 = findViewById(R.id.module_8);

        // Load an ad into the AdMob banner view.
        FrameLayout adContainerView = findViewById(R.id.ad_view_container);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        adContainerView.addView(adView);
        loadBanner();

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



        // When user taps Divide by 3-digits by 2-digits button navigate to the respective activity
        module_8.setOnClickListener(view -> {
            Intent intent = new Intent(this, DivisionModule8.class);
            startActivity(intent);
        });

        // When user taps Divide by 2-digits by 2-digits button navigate to the respective activity
        module_7.setOnClickListener(view -> {
            Intent intent = new Intent(this, DivisionModule7.class);
            startActivity(intent);
        });

        // When user taps Divide by 2-digits by 1-digit button navigate to the respective activity
        module_6.setOnClickListener(view -> {
            Intent intent = new Intent(this, DivisionModule6.class);
            startActivity(intent);
        });

        // When user taps Divide Multiples of 10, 100, 1000 button navigate to the respective activity
        module_5.setOnClickListener(view -> {
            Intent intent = new Intent(this, DivisionModule5.class);
            startActivity(intent);
        });

        // When user taps Divide by 7,8,9 button navigate to the respective activity
        module_4.setOnClickListener(view -> {
            Intent intent = new Intent(this, DivisionModule4.class);
            startActivity(intent);
        });

        // When user taps Divide by 5 and 10 button navigate to the respective activity
        module_3.setOnClickListener(view -> {
            Intent intent = new Intent(this, DivisionModule3.class);
            startActivity(intent);
        });

        // When user taps Divide by 3 and 6 button navigate to the respective activity
        module_2.setOnClickListener(view -> {
            Intent intent = new Intent(this, DivisionModule2.class);
            startActivity(intent);
        });

        // When user taps Divide by 2 and 4 button navigate to the respective activity
        module_1.setOnClickListener(view -> {
            Intent intent = new Intent(this, DivisionModule1.class);
            startActivity(intent);
        });

    }

    // Called when leaving the activity
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    // Called when returning to the activity
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    // Called before the activity is destroyed
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    private void loadBanner() {
        // Create an ad request. Check your logcat output for the hashed device ID
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = getAdSize();
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);
        // Step 5 - Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

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
            String shareBody = "Check out this fun math app! Practice addition, subtraction, multiplication or division." +  "\n"+  "\n"
                    + "Google Play store:" +  "\n" +"https://play.google.com/store/apps/details?id=com.transposesolutions.justnumbers&hl=en" + "\n";
            String shareSub = "Check out this fun math app from Transpose Solutions";
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

}