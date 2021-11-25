package com.transposesolutions.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Mass extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Declare the instance variable Ad view
    private AdView adView;
    private ActionBarDrawerToggle mToggle;
    EditText UserInput;
    TextView result1,result2,result3,result4,result5,result6,result7,result8,result9,result10;
    Spinner selector_1;
    double x=0;
    double userValue1=0;
    String selectedItem;
    DecimalFormat decimalFormat = new DecimalFormat("#.#####");
    DecimalFormat decimalScientificFormat = new DecimalFormat("#.#####E0");

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
            if (editable.length() == 0){
                result1.setText("");
                result2.setText("");
                result3.setText("");
                result4.setText("");
                result5.setText("");
                result6.setText("");
                result7.setText("");
                result8.setText("");
                result9.setText("");
                result10.setText("");

            }else{
                checkForChanges1();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mass);

        //UI elements
        UserInput = findViewById(R.id.user_input);
        selector_1 = findViewById(R.id.selector_1);
        result1=findViewById(R.id.Result_1);
        result2=findViewById(R.id.Result_2);
        result3=findViewById(R.id.Result_3);
        result4=findViewById(R.id.Result_4);
        result5=findViewById(R.id.Result_5);
        result6=findViewById(R.id.Result_6);
        result7=findViewById(R.id.Result_7);
        result8=findViewById(R.id.Result_8);
        result9=findViewById(R.id.Result_9);
        result10=findViewById(R.id.Result_10);

        // to perform the default logic on create
        int defaultValue1 = 1;
        String displayValue1 = String.valueOf(defaultValue1);
        UserInput.setText(displayValue1);

        // set listeners
        UserInput.addTextChangedListener(mTextWatcher);
        checkForChanges1();

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
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_Mass_items, R.layout.spinner_selector_items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Apply the adapter to the spinner
        selector_1.setAdapter( adapter);
        selector_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) parent.getItemAtPosition(position);
                String activityTextValue = UserInput.getText().toString();
                try {
                    userValue1 = Double.parseDouble(activityTextValue);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if(position==0){
                    update_Metric_Ton();
                }else if(position==1){
                    update_Kilogram();
                }else if(position==2){
                    update_Gram();
                }else if(position==3){
                    update_Milligram();
                }else if (position==4){
                    update_Micro_gram();
                }else if(position==5){
                    update_Short_ton();
                }else if(position==6){
                    update_Long_Ton();
                }else if(position==7){
                    update_Stone();
                }else if(position==8){
                    update_Pound();
                }else if(position==9){
                    update_Ounces();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void checkForChanges1() {
        // First get the edit text value from the activity
        String activityTextValue = UserInput.getText().toString();
        try {
            userValue1 = Double.parseDouble(activityTextValue);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        // code inside the button click event handler
        String spinnerValue = String.valueOf(selector_1.getSelectedItem());
        switch (spinnerValue) {
            case "Metric Ton (t)":
                x = 1;
                break;
            case "Kilogram (kg)":
                x = 2;
                break;
            case "Gram (g)":
                x = 3;
                break;
            case "Milligram (mg)":
                x = 4;
                break;
            case "Micro gram (μg) ":
                x = 5;
                break;
            case "Short ton (t – US)":
                x = 6;
                break;
            case "Long Ton (t – UK)":
                x = 7;
                break;
            case "Stone (st -UK)":
                x = 8;
                break;
            case "Pound (lbs)":
                x = 9;
                break;
            case "Ounces (oz)":
                x = 10;
                break;
            default:
                break;
        }
        if(x==1){
            update_Metric_Ton();
        }else if(x==2){
            update_Kilogram();
        }else if(x==3){
            update_Gram();
        }else if(x==4){
            update_Milligram();
        }else if(x==5){
            update_Micro_gram();
        }else if(x==6){
            update_Short_ton();
        }else if(x==7){
            update_Long_Ton();
        }else if(x==8){
            update_Stone();
        }else if(x==9){
            update_Pound();
        }else if(x==10){
            update_Ounces();
        }

    }

    private void update_Metric_Ton() {
        // Metric_Ton to Metric Ton (t)
        int  valueSelector1 = (int) (userValue1 * 1);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Metric_Ton to Kilogram (kg)
        int valueSelector2 = (int) (userValue1 *  1000);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Metric_Ton to Gram (g)
        double valueSelector3 =  (userValue1 *  1e+6);
        String _valueSelector3 = decimalScientificFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Metric_Ton to Milligram (mg)
        double valueSelector4 =  (userValue1 * 1e+9);
        String _valueSelector4 = decimalScientificFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Metric_Ton to Micro gram (μg)
        double valueSelector5 =  (userValue1 *  1e+12);
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Metric_Ton to Short ton (t – US)
        double valueSelector6 =  (userValue1 *  1.10231);
        String _valueSelector6 = decimalFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Metric_Ton to Long Ton (t – UK)
        double valueSelector7 = userValue1/1.016;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Metric_Ton to Stone (st -UK)
        double valueSelector8 = userValue1 * 157.473;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Metric_Ton to Pound (lbs)
        double valueSelector9 =  (userValue1 * 2204.62);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Metric_Ton to  Ounces (oz)
        double valueSelector10 =  (userValue1 * 35274);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
    }

    private void update_Kilogram() {
        // Kilogram to Metric Ton (t)
        double  valueSelector1 =  (userValue1 /1000);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Kilogram to Kilogram (kg)
        int valueSelector2 = (int) (userValue1 *  1);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Kilogram to Gram (g)
        int valueSelector3 = (int) (userValue1 * 1000);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Kilogram to Milligram (mg)
        double valueSelector4 =  (userValue1 * 1e+6);
        String _valueSelector4 = decimalScientificFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Kilogram to Micro gram (μg)
        double valueSelector5 =  (userValue1 *  1e+9);
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Kilogram to Short ton (t – US)
        double valueSelector6 =  (userValue1 /907);
        String _valueSelector6 = decimalFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Kilogram to Long Ton (t – UK)
        double valueSelector7 = userValue1 /1016;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Kilogram to Stone (st -UK)
        double valueSelector8 = userValue1 /6.35;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Kilogram to Pound (lbs)
        double valueSelector9 = (userValue1 * 2.20462);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Kilogram to  Ounces (oz)
        double valueSelector10 =  (userValue1 * 35.274);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
    }

    private void update_Gram() {
        // Gram to Metric Ton (t)
        double  valueSelector1 =  (userValue1 /1e+6);
        String _valueSelector1 = decimalScientificFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Gram to Kilogram (kg)
        double valueSelector2 =  (userValue1 /1000);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Gram to Gram (g)
        int valueSelector3 = (int) (userValue1 * 1);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Gram to Milligram (mg)
        double valueSelector4 =  (userValue1 * 1000);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Gram to Micro gram (μg)
        double valueSelector5 =  (userValue1 *  1e+6);
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Gram to Short ton (t – US)
        double valueSelector6 =  (userValue1 *  1.1023e-6);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Gram to Long Ton (t – UK)
        double valueSelector7 = userValue1 * 9.8421e-7;
        String _valueSelector7 = decimalScientificFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Gram to Stone (st -UK)
        double valueSelector8 = userValue1 / 6350;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Gram to Pound (lbs)
        double valueSelector9 =  (userValue1 /454);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Gram to  Ounces (oz)
        double valueSelector10 = (userValue1 /28.35);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
    }

    private void update_Milligram() {
        // Milligram to Metric Ton (t)
        double  valueSelector1 =  (userValue1 / 1e+9);
        String _valueSelector1 = decimalScientificFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Milligram to Kilogram (kg)
        double valueSelector2 =  (userValue1 / 1e+6);
        String _valueSelector2 = decimalScientificFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Milligram to Gram (g)
        double valueSelector3 =  (userValue1 /1000);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Milligram to Milligram (mg)
        int valueSelector4 = (int) (userValue1 * 1);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Milligram to Micro gram (μg)
        int valueSelector5 = (int) (userValue1 *  1000);
        String _valueSelector5 = decimalFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Milligram to Short ton (t – US)
        double valueSelector6 =  (userValue1 *  1.1023e-9);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Milligram to Long Ton (t – UK)
        double valueSelector7 = userValue1 * 9.8421e-10;
        String _valueSelector7 = decimalScientificFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Milligram to Stone (st -UK)
        double valueSelector8 = userValue1 * 1.5747e-7;
        String _valueSelector8 = decimalScientificFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Milligram to Pound (lbs)
        double valueSelector9 =  (userValue1 * 2.2046e-6);
        String _valueSelector9 = decimalScientificFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Milligram to  Ounces (oz)
        double valueSelector10 = (userValue1 * 3.5274e-5);
        String _valueSelector10 = decimalScientificFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
    }

    private void update_Micro_gram() {
        // Micro_gram to Metric Ton (t)
        double  valueSelector1 =  (userValue1 /1e+12);
        String _valueSelector1 = decimalScientificFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Micro_gram to Kilogram (kg)
        double valueSelector2 =  (userValue1/1e+9);
        String _valueSelector2 = decimalScientificFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Micro_gram to Gram (g)
        double valueSelector3 =  (userValue1 /1e+6);
        String _valueSelector3 = decimalScientificFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Micro_gram to Milligram (mg)
        double valueSelector4 =  (userValue1 /1000);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Micro_gram to Micro gram (μg)
        int valueSelector5 = (int) (userValue1 *  1);
        String _valueSelector5 = decimalFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Micro_gram to Short ton (t – US)
        double valueSelector6 =  (userValue1 *  1.1023e-12);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Micro_gram to Long Ton (t – UK)
        double valueSelector7 = userValue1 * 9.8421e-13;
        String _valueSelector7 = decimalScientificFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Micro_gram to Stone (st -UK)
        double valueSelector8 = userValue1 * 1.5747e-10;
        String _valueSelector8 = decimalScientificFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Micro_gram to Pound (lbs)
        double valueSelector9 =  (userValue1 * 2.2046e-9);
        String _valueSelector9 = decimalScientificFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Micro_gram to  Ounces (oz)
        double valueSelector10 =  (userValue1 *  3.5274e-8);
        String _valueSelector10 = decimalScientificFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
    }

    private void update_Short_ton() {
        // Short_ton to Metric Ton (t)
        double  valueSelector1 =  (userValue1 /1.102);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Short_ton to Kilogram (kg)
        double valueSelector2 =  (userValue1 *  907.185);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Short_ton to Gram (g)
        double valueSelector3 =  (userValue1 * 907185);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Short_ton to Milligram (mg)
        double valueSelector4 =  (userValue1 *  9.072e+8);
        String _valueSelector4 = decimalScientificFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Short_ton to Micro gram (μg)
        double valueSelector5 = (userValue1 *  9.072e+11);
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Short_ton to Short ton (t – US)
        int valueSelector6 = (int) (userValue1 *  1);
        String _valueSelector6 = decimalFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Short_ton to Long Ton (t – UK)
        double valueSelector7 = userValue1 /1.12;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Short_ton to Stone (st -UK)
        double valueSelector8 = userValue1 * 142.857;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Short_ton to Pound (lbs)
        int valueSelector9 = (int) (userValue1 * 2000);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Short_ton to  Ounces (oz)
        int valueSelector10 = (int) (userValue1 * 32000);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
    }

    private void update_Long_Ton() {
        // Long_Ton to Metric Ton (t)
        double  valueSelector1 =  (userValue1 * 1.01605);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Long_Ton to Kilogram (kg)
        double valueSelector2 =  (userValue1 *  1016.05);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Long_Ton to Gram (g)
        double valueSelector3 =  (userValue1 * 1.016e+6);
        String _valueSelector3 = decimalScientificFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Long_Ton to Milligram (mg)
        double valueSelector4 =  (userValue1 * 1.016e+9);
        String _valueSelector4 = decimalScientificFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Long_Ton to Micro gram (μg)
        double valueSelector5 =  (userValue1 *  1.016e+12);
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Long_Ton to Short ton (t – US)
        double valueSelector6 =  (userValue1 *  1.12);
        String _valueSelector6 = decimalFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Long_Ton to Long Ton (t – UK)
        int valueSelector7 = (int) (userValue1 * 1);
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Long_Ton to Stone (st -UK)
        int valueSelector8 = (int) (userValue1 * 160);
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Long_Ton to Pound (lbs)
        int valueSelector9 = (int) (userValue1 * 2240);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Long_Ton  to  Ounces (oz)
        int valueSelector10 = (int) (userValue1 * 35840);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
    }

    private void update_Stone() {
        // Stone to Metric Ton (t)
        double  valueSelector1 =  (userValue1 /157);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Stone to Kilogram (kg)
        double valueSelector2 =  (userValue1 *  6.35029);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Stone to Gram (g)
        double valueSelector3 =  (userValue1 * 6350.29);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Stone to Milligram (mg)
        double valueSelector4 =  (userValue1 * 6.35e+6);
        String _valueSelector4 = decimalScientificFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Stone to Micro gram (μg)
        double valueSelector5 =  (userValue1 * 6.35e+9);
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Stone to Short ton (t – US)
        double valueSelector6 =  (userValue1 /143);
        String _valueSelector6 = decimalFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Stone to Long Ton (t – UK)
        double valueSelector7 = userValue1 /160;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Stone to Stone (st -UK)
        int valueSelector8 = (int) (userValue1 * 1);
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Stone to Pound (lbs)
        int valueSelector9 = (int) (userValue1 * 14);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Stone to  Ounces (oz)
        int valueSelector10 = (int) (userValue1 * 224);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
    }

    private void update_Pound() {
        // Pound to Metric Ton (t)
        double  valueSelector1 =  (userValue1 /2205);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Pound to Kilogram (kg)
        double valueSelector2 =  (userValue1 /2.205);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Pound to Gram (g)
        double valueSelector3 =  (userValue1 * 453.592);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Pound to Milligram (mg)
        double valueSelector4 =  (userValue1 * 453592);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Pound to Micro gram (μg)
        double valueSelector5 =  (userValue1 *  4.536e+8);
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Pound to Short ton (t – US)
        double valueSelector6 =  (userValue1 /2000);
        String _valueSelector6 = decimalFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Pound to Long Ton (t – UK)
        double valueSelector7 = userValue1 /2240;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Pound to Stone (st -UK)
        double valueSelector8 = userValue1 /14;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Pound to Pound (lbs)
        int valueSelector9 = (int) (userValue1 * 1);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Pound to  Ounces (oz)
        int valueSelector10 = (int) (userValue1 * 16);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
    }

    private void update_Ounces() {
        // Ounces to Metric Ton (t)
        double  valueSelector1 =  (userValue1 * 2.835e-5);
        String _valueSelector1 = decimalScientificFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Ounces to Kilogram (kg)
        double valueSelector2 =  (userValue1 /35.274);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Ounces to Gram (g)
        double valueSelector3 =  (userValue1 * 28.3495);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Ounces to Milligram (mg)
        double valueSelector4 =  (userValue1 * 28349.5);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Ounces to Micro gram (μg)
        double valueSelector5 =  (userValue1 *  2.835e+7);
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Ounces to Short ton (t – US)
        double valueSelector6 =  (userValue1 *  3.125e-5);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Ounces to Long Ton (t – UK)
        double valueSelector7 = userValue1 * 2.7902e-5;
        String _valueSelector7 = decimalScientificFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Ounces to Stone (st -UK)
        double valueSelector8 = userValue1 /224;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Ounces to Pound (lbs)
        double valueSelector9 =  (userValue1 /16);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Ounces to  Ounces (oz)
        int valueSelector10 = (int) (userValue1 * 1);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
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
        getMenuInflater().inflate(R.menu.tool_menu,menu);
        return true;
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        // toolbar onOptionItemSelected
        if (item.getItemId() == R.id.action_share) {
            shareUpdate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareUpdate() {
        // current activity string are copied to another set of string that are made available for the Share intent
        String activity_result;
        activity_result =result1.getText().toString();
        String activity_result2;
        activity_result2 =result2.getText().toString();
        String activity_result3;
        activity_result3 = result3.getText().toString();
        String activity_result4;
        activity_result4 = result4.getText().toString();
        String activity_result5;
        activity_result5 = result5.getText().toString();
        String activity_result6;
        activity_result6 = result6.getText().toString();
        String activity_result7;
        activity_result7 = result7.getText().toString();
        String activity_result8;
        activity_result8 = result8.getText().toString();
        String activity_result9;
        activity_result9 = result9.getText().toString();
        String activity_result10;
        activity_result10 = result10.getText().toString();
        String User_enter_data;
        User_enter_data = UserInput.getText().toString();

        // above declared string are exported to share activity
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody =   "Results based on your input for "+ User_enter_data +" "+selectedItem + "\n"
                + "Metric Ton (t) : " + activity_result + "\n"
                + "Kilogram (kg) : " + activity_result2 + "\n"
                + "Gram (g) :" + activity_result3+ "\n"
                + "Milligram (mg) : " + activity_result4 + "\n"
                + "Micro gram (μg) : " + activity_result5  + "\n"
                + "Short ton (t – US)  :" + activity_result6+ "\n"
                + "Long Ton (t – UK): " + activity_result7 + "\n"
                + "Stone (st -UK) : " + activity_result8  + "\n"
                + "Pound (lbs) :" + activity_result9+ "\n"
                + "Ounces (oz) : " + activity_result10 + "\n";

        String shareSub = "Mass Converter Results Powered by Transpose Solutions Android App - Unit Converter";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));

    }


    // override method to listen for any click events on selecting a particular item from the drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.main_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_area) {
            Intent intent = new Intent(this, Area.class);
            startActivity(intent);
        } else if (id == R.id.menu_Digital_Storage) {
            Intent intent = new Intent(this, DigitalStorage.class);
            startActivity(intent);
        } else if (id == R.id.menu_energy) {
            Intent intent = new Intent(this, Energy.class);
            startActivity(intent);
        } else if (id == R.id.menu_force) {
            Intent intent = new Intent(this, Force.class);
            startActivity(intent);
        } else if (id == R.id.menu_Fuel_Economy) {
            Intent intent = new Intent(this, FuelEconomy.class);
            startActivity(intent);
        } else if (id == R.id.menu_frequency) {
            Intent intent = new Intent(this, Frequency.class);
            startActivity(intent);
        } else if (id == R.id.Length) {
            Intent intent = new Intent(this, Length.class);
            startActivity(intent);
        } else if (id == R.id.Mass) {
            Intent intent = new Intent(this, Mass.class);
            startActivity(intent);
        } else if (id == R.id.Plane_Angle) {
            Intent intent = new Intent(this, PlaneAngle.class);
            startActivity(intent);
        } else if (id == R.id.menu_power) {
            Intent intent = new Intent(this, Power.class);
            startActivity(intent);
        } else if (id == R.id.Pressure) {
            Intent intent = new Intent(this, Pressure.class);
            startActivity(intent);
        } else if (id == R.id.Speed) {
            Intent intent = new Intent(this, Speed.class);
            startActivity(intent);
        }
        else if (id == R.id.Temperature) {
            Intent intent = new Intent(this, Temperature.class);
            startActivity(intent);
        } else if (id == R.id.Time) {
            Intent intent = new Intent(this, Time.class);
            startActivity(intent);
        } else if (id == R.id.Volume) {
            Intent intent = new Intent(this, Volume.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_rate) {
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
            String shareBody = "Check out this great Unit Converter app. This app helps you to convert common units of measurement" +  "\n"+  "\n"
                    + "Google Play store:" +  "\n" +"https://play.google.com/store/apps/details?id=com.transposesolutions.unitconverter&hl=en" + "\n";
            String shareSub = "Check out this great Unit Converter app from Transpose Solutions";
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