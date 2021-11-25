package com.transposesolutions.justnumbers;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class DivisionModule2Result extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;
    private AdView adView;
    TextView mGrade, mFinalScore;
    Button mRetryButton, mHomeButton, mNextButton, mRedoButton;
    // declare variable - mInterstitialAd" - this to support method display Interstitial used outside the onCreate
    private InterstitialAd mInterstitialAd;
    int tableRows = 0;
    // database code
    public static MyAppDataBase myAppDataBase;
    UserDataTable userDataTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division_module2_result);

        myAppDataBase =  MyAppDataBase.getDbInstance(this.getApplicationContext());
        // Load an ad into the AdMob banner view.
        FrameLayout adContainerView = findViewById(R.id.ad_view_container);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        adContainerView.addView(adView);
        loadBanner();

        // Load Navigation View.
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Prepare the Interstitial Ad
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, String.valueOf(R.string.interstitial_ad_unit_id), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i("TAG", "onAdLoaded");
                displayInterstitial();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i("TAG", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });

        // Declare XML View
        mGrade = findViewById(R.id.greeting_message);
        mFinalScore = findViewById(R.id.score_display);
        mRetryButton = findViewById(R.id.retry);
        mNextButton = findViewById(R.id.next);
        mHomeButton = findViewById(R.id.home);
        mRedoButton = findViewById(R.id.redo);
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // When user taps retry button navigate to the game screen
        mRetryButton.setOnClickListener(view -> {
            AppTracker appTracker = (AppTracker) getApplicationContext();
            appTracker.setMistakeQuestions(true);
            myAppDataBase.myDao().deleteTable1(userDataTable);
            Intent intent = new Intent(this, DivisionModule2.class);
            startActivity(intent);
        });

        // When user taps next button navigate to the next game screen (Level/activity)
        mNextButton.setOnClickListener(view -> {
            AppTracker appTracker = (AppTracker) getApplicationContext();
            appTracker.setMistakeQuestions(true);
            myAppDataBase.myDao().deleteTable1(userDataTable);
            Intent intent = new Intent(this, DivisionModule3.class);
            startActivity(intent);
        });

        // When user taps home button navigate to the addition home screen
        mHomeButton.setOnClickListener(view -> {
            AppTracker appTracker = (AppTracker) getApplicationContext();
            appTracker.setMistakeQuestions(true);
            myAppDataBase.myDao().deleteTable1(userDataTable);
            Intent intent = new Intent(this, Division.class);
            startActivity(intent);
        });

        // When user taps redo mistake button navigate to the previous activity
        mRedoButton.setOnClickListener(view -> {
            AppTracker appTracker = (AppTracker) getApplicationContext();
            appTracker.setMistakeQuestions(false);
            Intent intent = new Intent(this, DivisionModule2.class);
            startActivity(intent);
        });


        // Using Bundle object retrieve data store from previous activity
        Bundle bundle = getIntent().getExtras();
        int score = Integer.parseInt(bundle.getString(DivisionModule2.GAME_MESSAGE1));
        String clockTime = bundle.getString(DivisionModule2.GAME_MESSAGE2);
        //Retrieve data value - Number of question from Database and print to Text View
        AppTracker appTracker = (AppTracker) getApplicationContext();
        int numberOfQuestions = appTracker.getNumberOfQuestions();
        boolean mClock = appTracker.getClockSettings();
        String PrintResult;
        if (mClock) {
            PrintResult = ("You scored " + score + " out of " + numberOfQuestions + " , Time: " + clockTime);
        } else {
            PrintResult = ("You scored " + score + " out of " + numberOfQuestions);
        }
        mFinalScore.setText(PrintResult);
        //Print the greeting based on the user score
        // Convert Score to scale of 100
        int formattedScore = score * 100 / numberOfQuestions;
        // if value in the range of 90 to 100
        if ((formattedScore > 90) && (score <= 100)) {
            mGrade.setText(R.string.result_greeting1);
        }
        // range 80 to 90
        else if ((formattedScore > 80) && (score <= 90)) {
            mGrade.setText(R.string.result_greeting2);
        }
        // range 70 to 80
        else if ((formattedScore > 70) && (score <= 80)) {
            mGrade.setText(R.string.result_greeting3);
        } else {
            mGrade.setText(R.string.result_greeting4);
        }
        UserDataTable[] reTryQuestions = myAppDataBase.myDao().retry();
        int size = reTryQuestions.length;
        if (size > 0) {
            mRedoButton.setVisibility(View.VISIBLE);
        } else {
            mRedoButton.setVisibility(View.INVISIBLE);
        }


        // Generate the columns and column heading
        String[] columns = new String[5];
        columns[0] = "Problem#";
        columns[1] = "Problem Set";
        columns[2] = "Input";
        columns[3] = "Result";
        columns[4] = "Answer";
        // Add table rows to the table
        this.addTableRow(tableLayout, columns);
        // Populate data to the table rows from database
        List<UserDataTable> users = myAppDataBase.myDao().getUsers1();
        for (UserDataTable userDataTable : users) {
            String[] _DisplayData = new String[5];
            String equalOperator = "   =";
            String Space1 = "  ";
            String Operator = "÷";
            // data for column-1
            _DisplayData[0] = String.valueOf(userDataTable.getId());
            int ids = userDataTable.getId();
            System.out.println(ids);
            // data for column-2
            String problemSet = userDataTable.getTopAddend1() + Space1 + Operator + Space1 + userDataTable.getTopAddend2() + equalOperator;
            _DisplayData[1] = problemSet;
            System.out.println(problemSet);
            // data for column-3
            _DisplayData[2] = String.valueOf(userDataTable.getUserInput());
            int userInput = userDataTable.getUserInput();
            System.out.println(userInput);
            // data for column-4
            _DisplayData[3] = String.valueOf(userDataTable.getStatus());
            String uniqueResult = userDataTable.getStatus();
            System.out.println(uniqueResult);
            // data for column-5
            _DisplayData[4] = String.valueOf(userDataTable.getAnswer());
            int problemKey = userDataTable.getAnswer();
            System.out.println(problemKey);
            addTableRow(tableLayout, _DisplayData);
        }
    }

    // Populate the table row based on the number of math problems
    private void addTableRow(TableLayout tableLayout, String[] text) {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        // Adding border to the Table
        tableRow.setBackgroundResource(R.drawable.row_border);
        //Add padding to the table
        tableRow.setPadding(5,15,5,15);
        // code to set alternative colors to the table rows
        int row = 0;
        if (tableRows== row)
        {
            tableRow.setBackgroundColor(tableRow.getContext().getResources().getColor(R.color.light_gray));

        }
        if (tableRows % 2 == 1) {
            tableRow.setBackgroundColor(tableRow.getContext().getResources().getColor(R.color.light_green));

        }
        for (String s : text) {
            TextView column = new TextView(this);
            column.setId(tableRows);
            column.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            column.setTextColor(Color.BLACK);
            column.setText(s);
            column.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.size));
            column.setGravity(Gravity.CENTER);
            tableRow.addView(column);
        }
        tableLayout.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        tableRows++;

    }

    // Called to display interstitial ad
    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when fullscreen content is dismissed.
                Log.d("TAG", "The ad was dismissed.");
            }

            @Override
            public void onAdFailedToShowFullScreenContent( AdError adError) {
                // Called when fullscreen content failed to show.
                Log.d("TAG", "The ad failed to show.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when fullscreen content is shown.
                // Make sure to set your reference to null so you don't
                // show it a second time.
                mInterstitialAd = null;
                Log.d("TAG", "The ad was shown.");
            }
        });
        if (mInterstitialAd != null) {
            mInterstitialAd.show(DivisionModule2Result.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

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
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.addition) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, Addition.class);
            startActivity(intent);
        } else if (id == R.id.subtraction) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, Subtraction.class);
            startActivity(intent);
        } else if (id == R.id.multiplication) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, Multiplication.class);
            startActivity(intent);
        } else if (id == R.id.division) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, Division.class);
            startActivity(intent);
        } else if (id == R.id.Settings) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, MyAppSettings.class);
            startActivity(intent);
        } else if (id == R.id.nav_rate) {
            try {
                appTracker.setMistakeQuestions(true);
                Uri uri1 = Uri.parse("market://details?id=" + getPackageName());
                Intent gotoMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket1);
            } catch (ActivityNotFoundException e){
                appTracker.setMistakeQuestions(true);
                Uri uri1 = Uri.parse("http://play.google.com/store/apps/details/id=" + getPackageName());
                Intent gotoMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket1);
            }
        } else if (id == R.id.nav_share) {
            appTracker.setMistakeQuestions(true);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Check out this fun math app! Math app to practice addition, subtraction, multiplication or division." +  "\n"+  "\n"
                    + "Google Play store:" +  "\n" +"https://play.google.com/store/apps/details?id=com.transposesolutions.justnumbers&hl=en" + "\n";
            String shareSub = "Check out this fun math app from Transpose Solutions";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        } else if (id == R.id.nav_apps) {
            try {
                appTracker.setMistakeQuestions(true);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(DivisionModule2Result.this);
        builder.setMessage("Are you sure you want to return to menu?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", (dialog, id) -> {
            AppTracker appTracker = (AppTracker) getApplicationContext();
            appTracker.setMistakeQuestions(true);
            myAppDataBase.myDao().deleteTable1(userDataTable);
            Intent intent = new Intent(DivisionModule2Result.this,Division.class);
            startActivity(intent);
        });
        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}