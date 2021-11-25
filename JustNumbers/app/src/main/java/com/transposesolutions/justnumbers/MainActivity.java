package com.transposesolutions.justnumbers;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;
    private AdView adView;
    ImageView mAddition, mSubtraction, mMultiplication, mDivision;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //For night mode theme
        setContentView(R.layout.activity_main);


        // Initiate Mobile Ads SDK
        MobileAds.initialize(this, initializationStatus -> {
        });

        // Declare XML View
        mAddition = findViewById(R.id.GameAddition);
        mSubtraction = findViewById(R.id.GameSubtraction);
        mMultiplication = findViewById(R.id.GameMultiple);
        mDivision = findViewById(R.id.GameDivision);

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

        // Called when the user taps addition button
        mAddition.setOnClickListener(view -> {
            Intent intent = new Intent(this, Addition.class);
            startActivity(intent);
        });

        // Called when the user taps subtraction button
        mSubtraction.setOnClickListener(view -> {
            Intent intent = new Intent(this, Subtraction.class);
            startActivity(intent);
        });

        // Called when the user taps multiplication button
        mMultiplication.setOnClickListener(view -> {
            Intent intent = new Intent(this, Multiplication.class);
            startActivity(intent);
        });

        // Called when the user taps division button
        mDivision.setOnClickListener(view -> {
            Intent intent = new Intent(this, Division.class);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", (dialog, id) -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        });
        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }


}