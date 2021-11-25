package com.transposesolutions.bankingcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;

public class ReturnModule1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
EditText  eAmountInvested,eAmountReturned ,eAmountLength,eTotalIncome,eTotalExpense;
TextView tTotalIncome,tTotalExpense,tAmountLength;

Button bCalculate ,bReset;
RadioButton radioButtonNo,radioButtonYes;
    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;
    private AdView adView;
    // declare package prefix , DEPOSIT_MESSAGE constant and key values
    public final static String _MESSAGE1 = "com.transposesolutions.bankingcalculator.ROI";
    public final static String _MESSAGE2 = "com.transposesolutions.bankingcalculator.ROI_A";
    public final static String _MESSAGE3 = "com.transposesolutions.bankingcalculator.INVESTED_GAIN";
    public final static String _MESSAGE4 = "com.transposesolutions.bankingcalculator.LENGTH";
    public final static String _MESSAGE5 = "com.transposesolutions.bankingcalculator.INVESTED";
    public final static String _MESSAGE6 = "com.transposesolutions.bankingcalculator.chartData";

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
        bCalculate = findViewById(R.id.button_calculate);
        String s1 = eAmountInvested.getText().toString();
        String s2 = eAmountReturned.getText().toString();
        String s3 = eAmountLength.getText().toString();

        if (s1.equals("") || s2.equals("") || s3.equals("") ) {
            bCalculate.setEnabled(false);
        }
        else {
            bCalculate.setEnabled(true);
        }}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_module1);
        // Load Navigation View, add toggle button to the drawer layout , setNavigationItemSelectedListener
        // and onOptionsItemSelected.
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Load an ad into the AdMob banner view.
        FrameLayout adContainerView = findViewById(R.id.ad_view_container);
        // Step 1 - Create an AdView and set the ad unit ID on it.
        adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.banner_ad_unit_id));
        adContainerView.addView(adView);
        loadBanner();

        // Capture the layout's EditText
        eAmountInvested = findViewById(R.id.user_input1);
        eAmountReturned = findViewById(R.id.user_input2);
        eAmountLength = findViewById(R.id.user_input3);
         bCalculate = findViewById(R.id.button_calculate);
         bReset = findViewById(R.id.reset);
         radioButtonNo = findViewById(R.id.radio_no);
         radioButtonYes = findViewById(R.id.radio_yes);
         eTotalIncome = findViewById(R.id.user_input4);
         eTotalExpense = findViewById(R.id.user_input5);
         tTotalIncome = findViewById(R.id.label_4);
         tTotalExpense = findViewById(R.id.label_5);
         tAmountLength = findViewById(R.id.label_3);


     radioButtonYes.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        eTotalIncome.setVisibility(View.VISIBLE);
        eTotalExpense.setVisibility(View.VISIBLE);
        tTotalIncome.setVisibility(View.VISIBLE);
        tTotalExpense.setVisibility(View.VISIBLE);
        radioButtonNo.setChecked(false);
    }
});
     radioButtonNo.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             eTotalIncome.setVisibility(View.GONE);
             eTotalExpense.setVisibility(View.GONE);
             tTotalIncome.setVisibility(View.GONE);
             tTotalExpense.setVisibility(View.GONE);

             radioButtonYes.setChecked(false);
         }
     });


        // set listeners
        eAmountInvested.addTextChangedListener(mTextWatcher);
        eAmountReturned.addTextChangedListener(mTextWatcher);
        eAmountLength.addTextChangedListener(mTextWatcher);
        // run once to disable if empty
        checkFieldsForEmptyValues();

        //check for required minimum value for Initial Invested (user_input1) Field
        eAmountInvested.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    // Retrieve Initial Invested Value from the activity
                    float _initialInvested = Float.parseFloat(eAmountInvested.getText().toString());
                    if (_initialInvested < 10000 ) {
                        eAmountInvested.setError("Please fill out this field! Enter a minimum value of 10000 or greater value");
                    }} catch (NumberFormatException e) {
                    e.printStackTrace();
                }}});
        //check for required minimum value for eAmountReturned (user_input1) Field
        eAmountReturned.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    // Retrieve eAmountReturned Value from the activity
                    float _amountReturned = Float.parseFloat(eAmountReturned.getText().toString());
                    if (_amountReturned < 10000 ) {
                        eAmountReturned.setError( "Please fill out this field! Enter a value equal or greater than investment amount");
                    }} catch (NumberFormatException e) {
                    e.printStackTrace();
                }}});
        //check for required minimum value for eTotalIncome (user_input1) Field
        eTotalIncome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    // Retrieve _totalIncome Value from the activity
                    float _totalIncome = Float.parseFloat(eTotalIncome.getText().toString());
                    if (_totalIncome < 0 ) {
                        eTotalIncome.setError("Please fill out this field! Enter a minimum value of 0 or greater value");
                    }} catch (NumberFormatException e) {
                    e.printStackTrace();
                }}});
        //check for required minimum value for tTotalExpense  Field
        eTotalExpense.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    // Retrieve tTotalExpense Value from the activity
                    float _totalExpense= Float.parseFloat(eTotalExpense.getText().toString());
                    if (_totalExpense < 0 ) {
                        eTotalExpense.setError("Please fill out this field! Enter a minimum value of 0 or greater value");
                    }} catch (NumberFormatException e) {
                    e.printStackTrace();
                }}});
        //check for required minimum value for eAmountLength  Field
        eAmountLength.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    // Retrieve eAmountLength Value from the activity
                    float _amountLength= Float.parseFloat(eAmountLength.getText().toString());
                    if (_amountLength < 1 ) {
                        eAmountLength.setError("Please fill out this field! Enter a minimum value of 1");
                    }} catch (NumberFormatException e) {
                    e.printStackTrace();
                }}});



        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReturnModule1.this,ReturnModule1.class));
            }
        });

        bCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                double _investedAmount = Double.parseDouble(eAmountInvested.getText().toString());
                double _returnAmount = Double.parseDouble(eAmountReturned.getText().toString());
                double _eAmountLength = Double.parseDouble(eAmountLength.getText().toString());
                float _totalIncome = Float.parseFloat(eTotalIncome.getText().toString());
                float _totalExpense= Float.parseFloat(eTotalExpense.getText().toString());

                if(_investedAmount < 10000){
                    requiredCheck1(); // _investedAmount check
                } else if(_returnAmount< 10000) {
                    requiredCheck2(); // _returnAmount check
                } else if(_totalIncome< 0){
                    requiredCheck3(); // _totalIncome check
                } else if(_totalExpense < 0){
                    requiredCheck4(); // _totalExpense check
                }else if(_eAmountLength <1){
                    requiredCheck5();  //_eAmountLength check

                }else {
                    double _cInvestment_gain = ((_returnAmount - _investedAmount)+_totalIncome-_totalExpense);
                    double _grossReturn = (_returnAmount + _totalIncome ) - _totalExpense; // return amount
                    double _cROI = (_cInvestment_gain) / _investedAmount * 100;
                    double _cROI_Annualized1 = Math.pow((_grossReturn / _investedAmount), (1 / _eAmountLength)) - 1;
                    double _cROI_Annualized = _cROI_Annualized1 * 100;


                    // The Intent constructor takes two parameters, a Context parameter and a subclass of Context the activity to start
                    Intent intent = new Intent(ReturnModule1.this, ReturnModule2.class);
                    //Android Bundle object and putExtra() Method is used to pass data between activities.
                    Bundle bundle = new Bundle();
                    String investedAmount= String.valueOf(_investedAmount);
                    String length = String.valueOf(_eAmountLength);
                    String ROI = String.valueOf(_cROI);
                    String ROI_Annualized = String.valueOf(_cROI_Annualized);
                    String Investment_gain = String.valueOf(_cInvestment_gain);
                    String returnAmount = String.valueOf(_grossReturn);
                    bundle.putString(_MESSAGE1, ROI);
                    bundle.putString(_MESSAGE2, ROI_Annualized);
                    bundle.putString(_MESSAGE3, Investment_gain);
                    bundle.putString(_MESSAGE4,length );
                    bundle.putString(_MESSAGE5,investedAmount );
                    bundle.putString(_MESSAGE6,returnAmount );

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void requiredCheck1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReturnModule1.this);
        builder.setMessage(" invested Amount must be at least 10000")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void requiredCheck2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReturnModule1.this);
        builder.setMessage("Return Amount must be equal or greater than investment amount")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void requiredCheck3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReturnModule1.this);
        builder.setMessage("Total Income must be at least 0 or greater")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void requiredCheck4() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReturnModule1.this);
        builder.setMessage("Total Expense must be at least 0 or greater")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void requiredCheck5() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReturnModule1.this);
        builder.setMessage("Amount Length must be at least 1 or greater")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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