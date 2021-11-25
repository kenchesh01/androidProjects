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
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class DepositModule5 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

// declare instance variables required for the navigation drawer and Adaptive Banner
    private ActionBarDrawerToggle mToggle;
    private AdView adView;
    // declare variable - mInterstitialAd" - this to support method display Interstitial used outside the onCreate
    private InterstitialAd mInterstitialAd;
    //Declare text view for column - result#1
    TextView _MaturityValue, _InterestEarned, _TotalDeposit, _APY, _Tax, _AfterTax, _NetMaturityValue, _InitialDeposit, _DepositTerm, _Rate, _Compounding,  _TaxRate;
    //Declare text view for column - result#2
    TextView MaturityValue, InterestEarned, TotalDeposit, APY, Tax, AfterTax, NetMaturityValue, InitialDeposit, DepositTerm, Rate, Compounding, TaxRate;
    private final String productKey = "banking_1010";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_module5);
//        Boolean validate = new ValidateStoreKey(this).getPurchaseKey(this,"myPref", productKey);
//        if(validate) {
//
//            ScrollView scrollView = findViewById(R.id.scroll);
//            DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams)scrollView.getLayoutParams();
//            // Set/Reset Margin for the Scroll View
//            layoutParams.topMargin = 10;
//            scrollView.setLayoutParams(layoutParams);
//            Toast.makeText(this, "you are a premium user", Toast.LENGTH_SHORT).show();
//        }else{
            Toast.makeText(this, "Free User", Toast.LENGTH_SHORT).show();
            // Load an ad into the AdMob banner view.
            FrameLayout adContainerView = findViewById(R.id.ad_view_container);
            // Step 1 - Create an AdView and set the ad unit ID on it.
            adView = new AdView(this);
            adView.setAdUnitId(getString(R.string.banner_ad_unit_id));
            adContainerView.addView(adView);
            loadBanner();
//        }

        // and onOptionsItemSelected.
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Prepare the Interstitial Ad
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, String.valueOf(R.string.interstitial_id),
                adRequest, new InterstitialAdLoadCallback() {
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



        // TextView for the #Result1
        _InitialDeposit = findViewById(R.id.initial_deposit_result1);
        _DepositTerm = findViewById(R.id.deposit_term_result1);
        _Rate = findViewById(R.id.rate_result1);
        _Compounding = findViewById(R.id.compounding_result1);
        _TaxRate = findViewById(R.id.tax_rate_result1);
        _MaturityValue = findViewById(R.id.maturity_value_result1);
        _InterestEarned = findViewById(R.id.total_interest_result1);
        _TotalDeposit = findViewById(R.id.total_payments_result1);
        _APY = findViewById(R.id.APY_result1);
        _Tax  = findViewById(R.id.tax_interest_result1);
        _AfterTax  = findViewById(R.id.interest_result1);
        _NetMaturityValue  = findViewById(R.id.net_maturity_result1);
        //#Result1
        // get the data values from DepositModule2.java
        SharedPreferences Deposit = getApplicationContext().getSharedPreferences("RESULT_DEPOSIT_PREFERENCES", MODE_PRIVATE);
        String _maturityValue = Deposit.getString(DepositModule2.DEPOSIT_RESULTS1,"");
        String _earnedInterestValue = Deposit.getString(DepositModule2.DEPOSIT_RESULTS2,"");
        double _totalDeposits = Double.parseDouble(Deposit.getString(DepositModule2.DEPOSIT_RESULTS3,""));
        String _yieldValue = Deposit.getString(DepositModule2.DEPOSIT_RESULTS4,"");
        String _taxValue = Deposit.getString(DepositModule2.DEPOSIT_RESULTS5,"");
        String _afterTaxValue = Deposit.getString(DepositModule2.DEPOSIT_RESULTS6,"");
        String _netMaturityValue = Deposit.getString(DepositModule2.DEPOSIT_RESULTS7,"");
        double _initialDepositValue = Double.parseDouble(Deposit.getString(DepositModule2.DEPOSIT_RESULTS8,""));
        String _depositTermValue = Deposit.getString(DepositModule2.DEPOSIT_RESULTS9,"");
        double _annualInterestValue = Double.parseDouble(Deposit.getString(DepositModule2.DEPOSIT_RESULTS10,""));
        String _compoundingValue = Deposit.getString(DepositModule2.DEPOSIT_RESULTS11,"");
        double _taxRateValue = Double.parseDouble(Deposit.getString(DepositModule2.DEPOSIT_RESULTS12,""));
        //setting the data values in the text view
        _InitialDeposit.setText(String.format(Locale.getDefault(),"%,.2f",_initialDepositValue));
        _DepositTerm.setText(_depositTermValue);
        _Rate.setText(String.format(Locale.getDefault(),"%,.2f",_annualInterestValue));
        _Compounding.setText(_compoundingValue);
        _TaxRate.setText(String.format(Locale.getDefault(),"%,.2f",_taxRateValue));
        _MaturityValue.setText(_maturityValue);
        _InterestEarned.setText(_earnedInterestValue);
        _TotalDeposit.setText(String.format(Locale.getDefault(),"%,.2f",_totalDeposits));
        _APY.setText(_yieldValue);
        _Tax.setText(_taxValue);
        _AfterTax.setText(_afterTaxValue);
        _NetMaturityValue.setText(_netMaturityValue);

        // TextView for the #Result2
        InitialDeposit = findViewById(R.id.initial_deposit_result2);
        DepositTerm = findViewById(R.id.deposit_term_result2);
        Rate = findViewById(R.id.rate_result2);
        Compounding = findViewById(R.id.compounding_result2);
        TaxRate = findViewById(R.id.tax_rate_result2);
        MaturityValue = findViewById(R.id.maturity_value_result2);
        InterestEarned = findViewById(R.id.total_interest_result2);
        TotalDeposit = findViewById(R.id.total_payments_result2);
        APY = findViewById(R.id.APY_result2);
        Tax  = findViewById(R.id.tax_interest_result2);
        AfterTax  = findViewById(R.id.interest_result2);
        NetMaturityValue  = findViewById(R.id.net_maturity_result2);
        //#Result2
        // get the data values from DepositModule4.java
        SharedPreferences compareDeposit = getApplicationContext().getSharedPreferences("DEPOSIT_SHARED_PREFERENCES", MODE_PRIVATE);
        double maturityValue = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE1,""));
        double earnedInterestValue = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE2,""));
        double totalDeposits = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE3,""));
        double yieldValue = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE4,""));
        double taxValue = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE5,""));
        double afterTaxValue = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE6,""));
        double netMaturityValue = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE7,""));
        double initialDepositValue = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE8,""));
        String depositTermValue = compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE9,"");
        double annualInterestValue = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE10,""));
        String compoundingValue = compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE11,"");
        double taxRateValue = Double.parseDouble(compareDeposit.getString(DepositModule4.DEPOSIT_COMPARE12,""));
        //setting the data values in the text view
        InitialDeposit.setText(String.format(Locale.getDefault(),"%,.2f",initialDepositValue));
        DepositTerm.setText(depositTermValue);
        Rate.setText(String.format(Locale.getDefault(),"%,.2f",annualInterestValue));
        Compounding.setText(compoundingValue);
        TaxRate.setText(String.format(Locale.getDefault(),"%,.2f",taxRateValue));
        MaturityValue.setText(String.format(Locale.getDefault(),"%,.2f",maturityValue));
        InterestEarned.setText(String.format(Locale.getDefault(),"%,.2f",earnedInterestValue));
        TotalDeposit.setText(String.format(Locale.getDefault(),"%,.2f",totalDeposits));
        APY.setText(String.format(Locale.getDefault(),"%,.2f",yieldValue));
        Tax.setText(String.format(Locale.getDefault(),"%,.2f",taxValue));
        AfterTax.setText(String.format(Locale.getDefault(),"%,.2f",afterTaxValue));
        NetMaturityValue.setText(String.format(Locale.getDefault(),"%,.2f",netMaturityValue));
    }

    // When user taps the share button, carry the values from screen and share
    public void activity_share() {

        //Result#1 Data to share
        String result1_maturity_value;
        result1_maturity_value = _MaturityValue.getText().toString();
        String result1_interest_earned;
        result1_interest_earned = _InterestEarned.getText().toString();
        String result1_total_deposits;
        result1_total_deposits = _TotalDeposit.getText().toString();
        String result1_yield;
        result1_yield = _APY.getText().toString();
        String result1_tax_interest;
        result1_tax_interest = _Tax.getText().toString();
        String result1_interest_after_tax;
        result1_interest_after_tax = _AfterTax.getText().toString();
        String result1_net_maturity;
        result1_net_maturity = _NetMaturityValue.getText().toString();
        String result1_initial_deposit;
        result1_initial_deposit = _InitialDeposit.getText().toString();
        String result1_deposit_term;
        result1_deposit_term = _DepositTerm.getText().toString();
        String result1_ROI;
        result1_ROI = _Rate.getText().toString();
        String result1_compounding_type;
        result1_compounding_type = _Compounding.getText().toString();
        String result1_income_Tax;
        result1_income_Tax = _TaxRate.getText().toString();

        //Result#2 Data to share
        String result2_maturity_value;
        result2_maturity_value = MaturityValue.getText().toString();
        String result2_interest_earned;
        result2_interest_earned = InterestEarned.getText().toString();
        String result2_total_deposits;
        result2_total_deposits = TotalDeposit.getText().toString();
        String result2_yield;
        result2_yield = APY.getText().toString();
        String result2_tax_interest;
        result2_tax_interest = Tax.getText().toString();
        String result2_interest_after_tax;
        result2_interest_after_tax = AfterTax.getText().toString();
        String result2_net_maturity;
        result2_net_maturity = NetMaturityValue.getText().toString();
        String result2_initial_deposit;
        result2_initial_deposit = InitialDeposit.getText().toString();
        String result2_deposit_term;
        result2_deposit_term = DepositTerm.getText().toString();
        String result2_ROI;
        result2_ROI = Rate.getText().toString();
        String result2_compounding_type;
        result2_compounding_type = Compounding.getText().toString();
        String result2_income_Tax;
        result2_income_Tax = TaxRate.getText().toString();


        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Result# 1" + ",\n" +
                "Your Estimated CD Balance:" + result1_maturity_value + ",\n" + "Total Interest Earned:" + result1_interest_earned + ",\n" + "Total Deposits:" + result1_total_deposits + ",\n" + "APY:" + result1_yield + "%" + ",\n"
                + "Tax on Interest Earned:" + result1_tax_interest + ",\n" + "Interest Earned after Tax:" + result1_interest_after_tax + ",\n" + "Net Savings Value:" + result1_net_maturity + ",\n"
                +  "\n" + "Above result is calculated based on your input:"
                +  "\n" + "Initial Deposit:"+ result1_initial_deposit + ",\n"  + "Deposit Term:" + result1_deposit_term + "months" +",\n" + "Annual Interest (Compounded):" + result1_ROI + "%" + ",\n"
                + "Compounding Type:"  + result1_compounding_type + ",\n" + "Your Tax Rate:" + result1_income_Tax + "%" +
                "\n" +"\n" + "Result# 2" + ",\n" +
                "Your Estimated CD Balance:" + result2_maturity_value + ",\n" + "Total Interest Earned:" + result2_interest_earned + ",\n" + "Total Deposits:" + result2_total_deposits + ",\n" + "APY:" + result2_yield + "%" + ",\n"
                + "Tax on Interest Earned:" + result2_tax_interest + ",\n" + "Interest Earned after Tax:" + result2_interest_after_tax + ",\n" + "Net Savings Value:" + result2_net_maturity + ",\n"
                +  "\n"+ "Above result is calculated based on your input:"
                +  "\n" + "Initial Deposit:"+ result2_initial_deposit + ",\n"  + "Deposit Term:" + result2_deposit_term + "months" +",\n" + "Annual Interest (Compounded):" + result2_ROI + "%" + ",\n"
                + "Compounding Type:" +  result2_compounding_type + ",\n" + "Your Tax Rate:" + result2_income_Tax + "%"  + ".\n";
        String shareSub = "Your Estimated Savings on Certificate Deposit Calculated by Transpose Solutions: Android App - Banking Calculator";
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
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
            mInterstitialAd.show(DepositModule5.this);
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

        }  else if (id == R.id.investedCalculator) {
            Intent intent = new Intent(this, ReturnModule1.class);
            startActivity(intent);
        }else if (id == R.id.nav_rate) {
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