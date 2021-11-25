package com.transposesolutions.financialratiocalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class TurnoverRatioModule1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

// declare instance variables required for the navigation drawer
private ActionBarDrawerToggle mToggle;
        EditText input1, input2, input3;
        Button buttonCalculate;
public final static String RESULT = "com.transposesolutions.financialratiocalculator.message_1";
public final static String DETAIL = "com.transposesolutions.financialratiocalculator.message_2";
public final static String USER_INPUT_1 = "com.transposesolutions.financialratiocalculator.message_3";
public final static String USER_INPUT_2 = "com.transposesolutions.financialratiocalculator.message_4";
public final static String USER_INPUT_3 = "com.transposesolutions.financialratiocalculator.message_5";
//  create a textWatcher member
private final TextWatcher mTextWatcher = new TextWatcher() {
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

        void checkFieldsForEmptyValues() {
        String s1 = input1.getText().toString();
        String s2 = input2.getText().toString();
        String s3 = input3.getText().toString();
        buttonCalculate.setEnabled(!s1.equals("") && !s2.equals("") && !s3.equals(""));
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turnover_ratio_module1);
        // Load Navigation View, add toggle button to the drawer layout , setNavigationItemSelectedListener
        // and onOptionsItemSelected.
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        //Declare UI elements
        input1 = findViewById(R.id.user_input1);
        input2 = findViewById(R.id.user_input2);
        input3 = findViewById(R.id.user_input3);
        buttonCalculate = findViewById(R.id.button_calculate);
        // set listeners
        input1.addTextChangedListener(mTextWatcher);
        input2.addTextChangedListener(mTextWatcher);
        input3.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();
        // Check for required values and alert user with message to fix the required values
        // validation for minimum value
        input1.setOnFocusChangeListener((v, hasFocus) -> {
            try {
                // Retrieve user input Value from the activity
                float net_sales = Float.parseFloat(input1.getText().toString());
                if (net_sales < 100) {
                    input1.setError("Please fill out this field! Enter a minimum value of 100 or greater value");
                }} catch (NumberFormatException e) {
                e.printStackTrace();
            }});

        // validation for minimum value
        input2.setOnFocusChangeListener((v, hasFocus) -> {
            try {
                // Retrieve user input Value from the activity
                float begin_total_assets = Float.parseFloat(input2.getText().toString());
                if (begin_total_assets < 100) {
                    input2.setError("Please fill out this field! Enter a minimum value of 100 or greater value");
                }} catch (NumberFormatException e) {
                e.printStackTrace();
            }});

        // validation for minimum value
        input3.setOnFocusChangeListener((v, hasFocus) -> {
            try {
                // Retrieve user input Value from the activity
                float end_total_assets = Float.parseFloat(input3.getText().toString());
                if (end_total_assets < 100) {
                    input3.setError("Please fill out this field! Enter a minimum value of 100 or greater value");
                }} catch (NumberFormatException e) {
                e.printStackTrace();
            }});

        buttonCalculate.setOnClickListener(view -> {
            // Retrieve user input (string) and convert them as float value
            float net_sales = Float.parseFloat(input1.getText().toString());
            float begin_total_assets = Float.parseFloat(input2.getText().toString());
            float end_total_assets = Float.parseFloat(input3.getText().toString());
            //check for required minimum value
            if (net_sales < 100) {
                requiredCheck1();
            }
            //check for required minimum value
            else if (begin_total_assets < 100) {
                requiredCheck2();
            }
            //check for required minimum value
            else if (end_total_assets < 100) {
                requiredCheck3();
            }else {
                // steps to calculate assets turnover ratio
                double average_total_assets = (begin_total_assets + end_total_assets)/2;
                double assets_turnover_ratio = (net_sales / average_total_assets);
                assets_turnover_ratio = Math.round(assets_turnover_ratio * 100.0)/100.0;
                System.out.println(assets_turnover_ratio);
                String _result =(assets_turnover_ratio + " : 1");
                // steps to display result details
                double result_detail = (net_sales / average_total_assets);
                result_detail = Math.round(result_detail * 100.0)/100.0;
                System.out.println(result_detail);
                String _detail =   "Based on the above result,  an asset turnover ratio of " + assets_turnover_ratio
                        + " means that for every dollar in assets, company generates $ " + result_detail + " in sales." ;

                // Method used to copy the above declared strings from this activity and forward it to another activity
                Intent intent = new Intent(TurnoverRatioModule1.this, TurnoverRatioModule2.class);
                Bundle bun = new Bundle();
                String message_1 = _result;
                String message_2 = _detail;
                String message_3 = input1.getText().toString();
                String message_4 = input2.getText().toString();
                String message_5 = input3.getText().toString();
                bun.putString(RESULT, message_1);
                bun.putString(DETAIL, message_2);
                bun.putString(USER_INPUT_1, message_3);
                bun.putString(USER_INPUT_2, message_4);
                bun.putString(USER_INPUT_3, message_5);
                intent.putExtras(bun);
                startActivity(intent);
            }
        });

    }

    // check for required minimum value for user input1
    private void requiredCheck1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TurnoverRatioModule1.this);
        builder.setMessage("Net sales value must be at least 100 or greater value")
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, i) -> {
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    // check for required minimum value for user input2
    private void requiredCheck2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TurnoverRatioModule1.this);
        builder.setMessage("Beginning total assets value must be at least 100 or greater value")
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, i) -> {
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    // check for required minimum value for user input3
    private void requiredCheck3() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TurnoverRatioModule1.this);
        builder.setMessage("Ending total assets value must be at least 100 or greater value")
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, i) -> {
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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
        int id = item.getItemId();
        if (id == R.id.main_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.profitability) {
            Intent intent = new Intent(this, Profitability.class);
            startActivity(intent);
        } else if (id == R.id.liquidity) {
            Intent intent = new Intent(this, Liquidity.class);
            startActivity(intent);
        } else if (id == R.id.efficiency) {
            Intent intent = new Intent(this, Efficiency.class);
            startActivity(intent);
        } else if (id == R.id.leverage) {
            Intent intent = new Intent(this, Leverage.class);
            startActivity(intent);
        } else if (id == R.id.market) {
            Intent intent = new Intent(this, Market.class);
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
            String shareBody = "Check out this financial ratio calculator: Finance utility app to analyze financial statement." +  "\n"+  "\n"
                    + "Google Play store:" +  "\n" +"https://play.google.com/store/apps/details?id=com.transposesolutions.financialratiocalculator&hl=en" + "\n";
            String shareSub = "Check out this financial ratio calculator from Transpose Solutions";
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
    // onClick Handler called when the user taps the button - Reset button
    public void activity_reset(View view) {
        Intent intent = new Intent(this, TurnoverRatioModule1.class);
        startActivity(intent);
    }
}