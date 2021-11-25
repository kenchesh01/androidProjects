package com.transposesolutions.bankingcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;
import java.util.Locale;

public class GoalModule2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;
    // declare package prefix , GOAL_MESSAGE constant, key values passed to GoalModule3 -Amortization Schedule
    public final static String GOAL_MESSAGE7 = "com.transposesolutions.bankingcalculator.SAVINGS_GOAL";
    public final static String GOAL_MESSAGE8 = "com.transposesolutions.bankingcalculator.INITIAL_INVESTMENT";
    public final static String GOAL_MESSAGE9 = "com.transposesolutions.bankingcalculator.TIME_SAVE";
    public final static String GOAL_MESSAGE10 = "com.transposesolutions.bankingcalculator.INTEREST_RATE";
    // Key values for storing and passing the values GoalModule4 - Compare Activity
    public final static String DISPLAY_MESSAGE1 = "com.transposesolutions.bankingcalculator.CONTRIBUTION";
    public final static String DISPLAY_MESSAGE1A = "com.transposesolutions.bankingcalculator.TOTAL_DEPOSIT";
    public final static String DISPLAY_MESSAGE2 = "com.transposesolutions.bankingcalculator.EARNED_INTEREST";
    public final static String DISPLAY_MESSAGE3 = "com.transposesolutions.bankingcalculator.GOAL";
    public final static String DISPLAY_MESSAGE4 = "com.transposesolutions.bankingcalculator.DEPOSIT";
    public final static String DISPLAY_MESSAGE5 = "com.transposesolutions.bankingcalculator.TERM";
    public final static String DISPLAY_MESSAGE6 = "com.transposesolutions.bankingcalculator.RATE";
    // declare TextView Widgets. Import the required class
    TextView result1, result2, returnResult,investResult;
    // This is an ad unit ID for a test ad. Replace with your own banner ad unit ID.
    private AdView adView;
    String mGoal,mInvestment,mTime,mInterest;
    private final String productKey = "banking_1010";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_module2);

        // Load Navigation View, add toggle button to the drawer layout , setNavigationItemSelectedListener
        // and onOptionsItemSelected.
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

            Toast.makeText(this, "Free User", Toast.LENGTH_SHORT).show();
            // Load an ad into the AdMob banner view.
            FrameLayout adContainerView = findViewById(R.id.ad_view_container);
            // Step 1 - Create an AdView and set the ad unit ID on it.
            adView = new AdView(this);
            adView.setAdUnitId(getString(R.string.banner_ad_unit_id));
            adContainerView.addView(adView);
            loadBanner();

        // Capture the layout's TextView Widget
        result1 = findViewById(R.id.result_1);
        result2 = findViewById(R.id.result_2);
        returnResult = findViewById(R.id.returnTextResult);
        investResult = findViewById(R.id.investedTextResult);
        ProgressBar pieChart = findViewById(R.id.stats_progressbar);

        // get the data values from GoalModule1.java
        Bundle bun = getIntent().getExtras();
        String CONTRIBUTION = bun.getString(GoalModule1.GOAL_MESSAGE1);
        String INTEREST = bun.getString(GoalModule1.GOAL_MESSAGE2);
        mGoal = bun.getString(GoalModule1.GOAL_MESSAGE3);
        mInvestment = bun.getString(GoalModule1.GOAL_MESSAGE4);
        mTime = bun.getString(GoalModule1.GOAL_MESSAGE5);
        mInterest = bun.getString(GoalModule1.GOAL_MESSAGE6);


        // Calculate the slice size and update the pie chart:
        double _cGain = Double.parseDouble(INTEREST) ;
        double MonthlyDeposit = Double.parseDouble(mInvestment);
        double SavingsTerm = Double.parseDouble(mTime);
        double depositRequired = Double.parseDouble(CONTRIBUTION);

        double amountInvested = MonthlyDeposit+(depositRequired*SavingsTerm);

        double _cReturn = (_cGain /(amountInvested+_cGain))*100;
        double _cInvested = (amountInvested / (amountInvested+ _cGain))*100;
        double chartData = _cReturn / _cInvested;

        int progress = (int) (chartData * 100);
        pieChart.setProgress(Integer.parseInt(String.valueOf(progress)));



        // convert the string to double then format the string to print the value to TextView
        double _depositRequired = Double.parseDouble(CONTRIBUTION);
        result1.setText(String.format(Locale.getDefault(),"%,.2f",_depositRequired));
        double _interestEarned = Double.parseDouble(INTEREST);
        result2.setText(String.format(Locale.getDefault(),"%,.2f",_interestEarned));
        String invest  =String.format(Locale.getDefault(),"%,.2f", _cInvested)+"%";
        investResult.setText(invest);
        String amountReturn =String.format(Locale.getDefault(),"%,.2f", _cReturn)+"%";
        returnResult.setText(amountReturn);
    }

    // onClick Handler called when the user taps the button - Share
    public void activity_share() {
        String mContribution;
        mContribution = result1.getText().toString();
        String mInterest_earned;
        mInterest_earned = result2.getText().toString();

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "To reach your savings goal, you need a estimated monthly deposit of :" + mContribution + ",\n" + "Total Interest Earned:" +mInterest_earned + ",\n"
                + "\n" + "Above result is calculated based on your input:"
                + "\n"+ "Savings Goal:"+ mGoal + ",\n" + "Initial Deposit:" + mInvestment + ",\n"
                + "Time to Save (Months):" + mTime + ",\n" + "Annual Interest:" + mInterest + "%" + ".\n";
        String shareSub = "Your estimated contribution required to meet your savings goal is Calculated by Transpose Solutions: Android App - Banking Calculator";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }


    // onClick Handler called when the user taps the button - compare
    public void activity_compare(View view) {
        Bundle bundle1 = getIntent().getExtras();
        String ACTUAL_DEPOSIT = bundle1.getString(GoalModule1.GOAL_MESSAGE1A);
        String mContribution;
        mContribution = result1.getText().toString();
        String mInterest_earned;
        mInterest_earned = result2.getText().toString();

        // using Intent send the use to Module 4 -  Compare Activity
        Intent intent = new Intent(GoalModule2.this, GoalModule4.class);
        // using shared preferences to store the credentials */
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SHARED_PREFERENCE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String CONTRIBUTION = mContribution;
        String INTEREST = mInterest_earned;
        String GOAL = mGoal;
        String INVESTMENT = mInvestment;
        String TIME = mTime;
        String ROI =  mInterest;
        editor.putString(DISPLAY_MESSAGE1, CONTRIBUTION);
        editor.putString(DISPLAY_MESSAGE1A, ACTUAL_DEPOSIT);
        editor.putString(DISPLAY_MESSAGE2, INTEREST);
        editor.putString(DISPLAY_MESSAGE3, GOAL);
        editor.putString(DISPLAY_MESSAGE4, INVESTMENT);
        editor.putString(DISPLAY_MESSAGE5, TIME);
        editor.putString(DISPLAY_MESSAGE6, ROI);
        /* Commits the changes and adds them to the file */
        editor.apply();
        startActivity(intent);
    }

    // onClick Handler called when the user taps the button - Amortization
    public void activity_amortization(View view) {
        // The Intent constructor takes two parameters, a Context parameter and a subclass of Context the activity to start
        Intent intent = new Intent(GoalModule2.this, GoalModule3.class);
        //Android Bundle object and putExtra() Method is used to pass data between activities.
        Bundle bundle2 = new Bundle();
        // get data values from the activity
//        String GOAL = result3.getText().toString();
//        String INVESTMENT = result4.getText().toString();
//        String TIME = result5.getText().toString();
//        String ROI =  result6.getText().toString();
        // pass data values from one activity to another
        bundle2.putString(GOAL_MESSAGE7, mGoal);
        bundle2.putString(GOAL_MESSAGE8, mInvestment);
        bundle2.putString(GOAL_MESSAGE9, mTime);
        bundle2.putString(GOAL_MESSAGE10, mInterest);
        intent.putExtras(bundle2);
        startActivity(intent);
    }

    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    /** Called to load ad */
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
// toolbar onOptionItemSelected
        if (item.getItemId() == R.id.action_share) {
            activity_share();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // override method to listen for any click events on selecting a particular item from the drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_home) {
            // Handle the camera action
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.CD) {
            Intent intent = new Intent(this, DepositModule1.class);
            startActivity(intent);
        } else if (id == R.id.savings) {
            Intent intent = new Intent(this, SavingsModule1.class);
            startActivity(intent);
        } else if (id == R.id.savings_goal) {
            Intent intent = new Intent(this, GoalModule1.class);
            startActivity(intent);
        } else if (id == R.id.investedCalculator) {
            Intent intent = new Intent(this, ReturnModule1.class);
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
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Check out this great Banking Calculator app. This app helps you to simulate your future savings based on the compound interest formula " +  "\n"+  "\n"
                    + "Google Play store:" +  "\n" +"https://play.google.com/store/apps/details?id=com.transposesolutions.bankingcalculator&hl=en" + "\n";
            String shareSub = "Check out this great Banking Calculator app from Transpose Solutions";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
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