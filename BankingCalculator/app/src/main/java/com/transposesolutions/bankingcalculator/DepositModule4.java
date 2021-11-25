package com.transposesolutions.bankingcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;

public class DepositModule4 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;
    private AdView adView;
    private final String productKey = "banking_1010";
    // declare package prefix , DEPOSIT_MESSAGE constant and key values
    public final static String DEPOSIT_COMPARE1 = "com.transposesolutions.bankingcalculator.MATURITY";
    public final static String DEPOSIT_COMPARE2 = "com.transposesolutions.bankingcalculator.INTEREST";
    public final static String DEPOSIT_COMPARE3 = "com.transposesolutions.bankingcalculator.TOTAL_DEPOSIT";
    public final static String DEPOSIT_COMPARE4 = "com.transposesolutions.bankingcalculator.YIELD";
    public final static String DEPOSIT_COMPARE5 = "com.transposesolutions.bankingcalculator.TAX";
    public final static String DEPOSIT_COMPARE6 = "com.transposesolutions.bankingcalculator.INTEREST_TAX";
    public final static String DEPOSIT_COMPARE7 = "com.transposesolutions.bankingcalculator.NET";
    public final static String DEPOSIT_COMPARE8 = "com.transposesolutions.bankingcalculator.DEPOSIT";
    public final static String DEPOSIT_COMPARE9 = "com.transposesolutions.bankingcalculator.TERM";
    public final static String DEPOSIT_COMPARE10 = "com.transposesolutions.bankingcalculator.ROI";
    public final static String DEPOSIT_COMPARE11 = "com.transposesolutions.bankingcalculator.COMPOUNDING";
    public final static String DEPOSIT_COMPARE12 = "com.transposesolutions.bankingcalculator.INCOME_TAX";
    // declare EditText, and Spinner Widgets. Import the required class
    EditText input1, input2, input3, input5;
    Spinner input4;
    // declare variable - SPINNER_VALUE and "i" used inside switch statement
    String SPINNER_VALUE;
    private double i=0;


    //  create a textWatcher member
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };
    private void checkFieldsForEmptyValues() {
        Button button = findViewById(R.id.button_calculate);
        String s1 = input1.getText().toString();
        String s2 = input2.getText().toString();
        String s3 = input3.getText().toString();
        String s4 = input5.getText().toString();

        if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")) {
            button.setEnabled(false);
        }
        else {
            button.setEnabled(true);
        }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_module4);
//        Boolean validate = new ValidateStoreKey(this).getPurchaseKey(this,"myPref", productKey);
//        if(validate) {
//
//            ScrollView scrollView = findViewById(R.id.scroll);
//            DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams)scrollView.getLayoutParams();
//            // Set/Reset Margin for the Scroll View
//            layoutParams.topMargin = 10;
//            scrollView.setLayoutParams(layoutParams);
//            Toast.makeText(this, "you are a premium user", Toast.LENGTH_SHORT).show();
//
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
         /*Load Navigation view, add toggle button to the drawer layout,
         setNavigationItemSelectedListener and onOptionsItemSelected.*/
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Capture the layout's EditText and Spinner Widget
        input1 = findViewById(R.id.user_input1);
        input2 = findViewById(R.id.user_input2);
        input3 = findViewById(R.id.user_input3);
        input4 = findViewById(R.id.user_input4);
        input5 = findViewById(R.id.user_input5);
        final Button calculateButton = findViewById(R.id.button_calculate);

        //use Array Adapter to make UI changes to Spinner Widget
        ArrayAdapter<CharSequence> custom = ArrayAdapter.createFromResource(this,
                R.array.compounding_arrays, R.layout.spinner_layout);
        custom.setDropDownViewResource(R.layout.spinner_layout);
        input4.setAdapter(custom);

        // set listeners
        input1.addTextChangedListener(mTextWatcher);
        input2.addTextChangedListener(mTextWatcher);
        input3.addTextChangedListener(mTextWatcher);
        input5.addTextChangedListener(mTextWatcher);
        // run once to disable if empty
        checkFieldsForEmptyValues();

        // Check for minimum and maximum required values and alert user with message to fix the required values
        //check for required minimum value for Initial Deposit (user_input1) Field
        input1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    // Retrieve Initial Deposit Value from the activity
                    float _initialDeposit = Float.parseFloat(input1.getText().toString());
                    if (_initialDeposit < 100) {
                        input1.setError("Please fill out this field! Enter a minimum value of 100 or greater value");
                    }} catch (NumberFormatException e) {
                    e.printStackTrace();
                }}});
        // check for required minimum value for deposit term (user_input2) Field
        input2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    // Retrieve Deposit Term Value from the activity
                    float _depositTerm = Float.parseFloat(input2.getText().toString());
                    if (_depositTerm < 1) {
                        input2.setError("Please fill out this field! Enter a minimum value of 1");
                    }} catch (NumberFormatException e) {
                    e.printStackTrace();
                }}});
        //check for required minimum value for annual interest rate (user_input3) Field
        input3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    // Retrieve Annual Rate of Interest (r) from the activity
                    float _annualInterestRate = Float.parseFloat(input3.getText().toString());
                    if (_annualInterestRate < 0.1) {
                        input3.setError("Please fill out this field! Enter a minimum value of 0.01 or greater");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }}});
        //check for required maximum value for annual interest rate (user_input3) Field
        input3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    float _annualInterestRate = Float.parseFloat(input3.getText().toString());
                    if (_annualInterestRate > 50){
                        input3.setError("Enter a value in the range of 0.01 to 50");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }}});
        //check for required minimum value for tax rate (user_input5) Field
        input5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    input5.setError("Please fill out this field! Enter a minimum value of 0 ot greater ");
                }}});

        // OnClick listener to handle the event when the user taps the Button - Calculate
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CD Calculator formula (Excel): Using the function PMT(rate,NPER,PV,FV)
                // The rate argument is annual interest rate (%).
                // The NPER argument is time to save.
                // The PV (present value) is initial deposit.
                // The FV (future value) is Savings Goal.
                // Our logic to generate result based on the  above formula:
                // Retrieve Savings Goal Value from the activity
                SPINNER_VALUE = (String)input4.getSelectedItem();
                switch(SPINNER_VALUE) {
                    case "Daily":
                        i=365;
                        break;
                    case "Monthly":
                        i = 12;
                        break;
                    case "Quarterly":
                        i = 4;
                        break;
                    case "Semi-Annually":
                        i = 2;
                        break;
                    case "Annually":
                        i = 1;
                        break;
                    default:
                        break;
                }
                // Initial Amount of Deposit
                float _initial_Deposit = Float.parseFloat(input1.getText().toString());
                // Number of Months in terms of year
                float _term = Float.parseFloat(input2.getText().toString());
                double _deposit_Term = _term/12;
                // Annual Rate of Interest
                float interest_rate = Float.parseFloat(input3.getText().toString());
                double _annual_interest = interest_rate / 100;
                // Tax Rate
                float tax_rate = Float.parseFloat(input5.getText().toString());
                double _taxRate = tax_rate / 100;
                // Interest Compounding Type (Daily, Monthly, Quarterly, Semi-annually & Annually)
                double _timesPerYear = i;
                // Check for minimum and maximum required values and alert user with message to fix the required values
                if(_initial_Deposit < 100){
                    requiredCheck1();
                }
                //check for required minimum value for deposit term
                else if(_term< 1){
                    requiredCheck2();
                }
                //check for required minimum value for annual interest rate
                else if(interest_rate< 0.1){
                    requiredCheck3();
                }
                //check for required maximum value for annual interest rate
                else if(interest_rate > 50){
                    requiredCheck4();
                }
                // Steps to calculate - Monthly deposit  & Interest earned
                else {
                    // Steps to calculate - Annual Savings & Interest
                    double _step1 = 1+ (_annual_interest / _timesPerYear);
                    double _step2 =  _timesPerYear * _deposit_Term;
                    double _maturity_Value = _initial_Deposit * Math.pow(_step1,_step2);
                    _maturity_Value = Math.round(_maturity_Value*100.0)/100.0;
                    //System.out.println(_maturity_Value);
                    double _interest_Earned = _maturity_Value - _initial_Deposit;
                    _interest_Earned = Math.round(_interest_Earned * 100.0)/100.0;

                    // Steps to calculate - APY
                    double _step3 = 1 + _annual_interest / _timesPerYear;
                    double _step4 =  Math.pow(_step3, _timesPerYear) - 1;
                    double _result_APY = Math.round((_step4 * 100.0) * 100) / 100.0;

                    // Steps to calculate - Tax on Interest Earned
                    double _taxable_Interest = _interest_Earned * _taxRate;
                    _taxable_Interest = Math.round(_taxable_Interest * 100.0)/100.0;

                    // Steps to calculate - Net Interest After Tax Expense
                    double _net_Interest = _interest_Earned - _taxable_Interest;
                    _net_Interest = Math.round(_net_Interest * 100.0)/100.0;
                    // Steps to calculate - Net Maturity Value After Tax Expense
                    double _net_Maturity = _maturity_Value - _taxable_Interest;
                    _net_Maturity = Math.round(_net_Maturity * 100.0)/100.0;

                    // The Intent constructor takes two parameters, a Context parameter and a subclass of Context the activity to start
                    Intent intent = new Intent(DepositModule4.this, DepositModule5.class);
                    // using shared preferences to store the credentials */
                    SharedPreferences depositSharedPreferences = getApplicationContext().getSharedPreferences("DEPOSIT_SHARED_PREFERENCES", MODE_PRIVATE);
                    SharedPreferences.Editor editor = depositSharedPreferences.edit();
                    String MATURITY = String.valueOf(_maturity_Value);
                    String TOTAL_DEPOSITS = input1.getText().toString();
                    String INTEREST = String.valueOf(_interest_Earned);
                    String YIELD = String.valueOf(_result_APY);
                    String TAX = String.valueOf(_taxable_Interest);
                    String INTEREST_TAX = String.valueOf(_net_Interest);
                    String NET = String.valueOf(_net_Maturity);
                    String DEPOSIT = input1.getText().toString();
                    String TERM = input2.getText().toString();
                    String ROI = input3.getText().toString();
                    String COMPOUNDING = SPINNER_VALUE;
                    String INCOME_TAX =  input5.getText().toString();
                    editor.putString(DEPOSIT_COMPARE1, MATURITY);
                    editor.putString(DEPOSIT_COMPARE2, INTEREST);
                    editor.putString(DEPOSIT_COMPARE3, TOTAL_DEPOSITS);
                    editor.putString(DEPOSIT_COMPARE4, YIELD);
                    editor.putString(DEPOSIT_COMPARE5, TAX);
                    editor.putString(DEPOSIT_COMPARE6, INTEREST_TAX);
                    editor.putString(DEPOSIT_COMPARE7, NET);
                    editor.putString(DEPOSIT_COMPARE8, DEPOSIT);
                    editor.putString(DEPOSIT_COMPARE9, TERM);
                    editor.putString(DEPOSIT_COMPARE10, ROI);
                    editor.putString(DEPOSIT_COMPARE11, COMPOUNDING);
                    editor.putString(DEPOSIT_COMPARE12, INCOME_TAX);
                    /* Commits the changes and adds them to the file */
                    editor.apply();
                    startActivity(intent);
                }}
        });
    }

    //check for required minimum value for initial deposit
    private void requiredCheck1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DepositModule4.this);
        builder.setMessage("Initial deposit must be at least 100")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // check for required minimum value for deposit term
    private void requiredCheck2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DepositModule4.this);
        builder.setMessage("Deposit term must be above zero")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    //check for required minimum value for annual interest rate
    private void requiredCheck3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DepositModule4.this);
        builder.setMessage("Annual Interest must be at least 0.01 or greater")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    //check for required maximum value for annual interest rate
    private void requiredCheck4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DepositModule4.this);
        builder.setMessage("Annual Interest must be in the range of 0.01 to 50")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    // onClick Handler called when the user taps the button - reset
    public void activity_reset(View view) {
        Intent intent = new Intent(this, DepositModule4.class);
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
    public  boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
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