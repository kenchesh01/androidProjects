package com.transposesolutions.unitconverter;

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

public class Length extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Declare the instance variable Ad view
    private AdView adView;
    private ActionBarDrawerToggle mToggle;
    EditText UserInput;
    TextView result1,result2,result3,result4,result5,result6,result7,result8,result9,result10,result11;
    Spinner selector_1;
    double x=0;
    double userValue1=0;
    String selectedItem;
    DecimalFormat decimalFormat = new DecimalFormat("#.######");
    DecimalFormat decimalScientificFormat = new DecimalFormat("#.#####E0");

    //  create a textWatcher member
    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {  }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {  }

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
                result11.setText("");
            }else{
                checkForChanges1();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

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
        result11=findViewById(R.id.Result_11);

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
                R.array.spinner_length_items, R.layout.spinner_selector_items);
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
                    update_Kilometer();
                }else if(position==1){
                    update_Meter();
                }else if(position==2){
                    update_Centimeter();
                }else if(position==3){
                    update_Millimeter();
                }else if (position==4){
                    update_Micrometer();
                }else if(position==5){
                    update_Nanometer();
                }else if(position==6){
                    update_Mile();
                }else if(position==7){
                    update_Yard();
                }else if(position==8){
                    update_Foot();
                }else if(position==9){
                    update_Inch();
                }else if(position==10){
                    update_Nautical_Mile();
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
            case "Kilometer (km)":
                x = 1;
                break;
            case "Meter (m)":
                x = 2;
                break;
            case "Centimeter (cm)":
                x = 3;
                break;
            case "Millimeter (mm)":
                x = 4;
                break;
            case "Micrometer (μm)":
                x = 5;
                break;
            case "Nanometer (nm)":
                x = 6;
                break;
            case "Mile (mi)":
                x = 7;
                break;
            case "Yard (yd)":
                x = 8;
                break;
            case "Foot (ft)":
                x = 9;
                break;
            case "Inch (in)":
                x = 10;
                break;
            case "Nautical Mile (NM)":
                x = 11;
                break;
            default:
                break;
        }
        if(x==1){
            update_Kilometer();
        }else if(x==2){
            update_Meter();
        }else if(x==3){
            update_Centimeter();
        }else if(x==4){
            update_Millimeter();
        }else if(x==5){
            update_Micrometer();
        }else if(x==6){
            update_Nanometer();
        }else if(x==7){
            update_Mile();
        }else if(x==8){
            update_Yard();
        }else if(x==9){
            update_Foot();
        }else if(x==10){
            update_Inch();
        }else if(x==11){
            update_Nautical_Mile();
        }
    }

    private void update_Kilometer() {
        //Kilometer to Kilometer
        int  valueSelector1 = (int) (userValue1 * 1);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Kilometer to Meter
        int valueSelector2 = (int) (userValue1 * 1000);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Kilometer to Centimeter
        int valueSelector3 = (int) (userValue1 * 100000);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Kilometer to Millimeter
        double valueSelector4 =  (userValue1 * 1e+6);
        String _valueSelector4 = decimalScientificFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Kilometer to Micrometer
        double valueSelector5 = userValue1 *  1e+9;
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Kilometer to Nanometer
        double valueSelector6 =  (userValue1 *  1e+12);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Kilometer to Mile
        double valueSelector7 = userValue1 /1.609;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Kilometer to Yard
        double valueSelector8 = userValue1 * 1093.613;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Kilometer to Foot
        double valueSelector9 =  (userValue1 * 3280.84);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Kilometer to inch
        double valueSelector10 =  (userValue1 * 39370.1);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Kilometer to Nautical mile
        double valueSelector11 = userValue1 /1.852;
        String _valueSelector11 = decimalFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Meter() {
        //Meter to Kilometer
        double  valueSelector1 =  (userValue1 /1000);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Meter to Meter
        int valueSelector2 = (int) (userValue1 * 1);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Meter to Centimeter
        int valueSelector3 = (int) (userValue1 * 100);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Meter to Millimeter
        int valueSelector4 = (int) (userValue1 * 1000);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Meter to Micrometer
        double valueSelector5 = (userValue1 *  1e+6);
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Meter to Nanometer
        double valueSelector6 =  (userValue1 *  1e+9);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Meter to Mile
        double valueSelector7 = userValue1 /1609;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Meter to Yard
        double valueSelector8 = userValue1 *  1.09361;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Meter to Foot
        double valueSelector9 =  (userValue1 * 3.28084);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Meter to inch
        double valueSelector10 =  (userValue1 * 39.3701);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Meter to Nautical mile
        double valueSelector11 = userValue1 /1852;
        String _valueSelector11 = decimalFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Centimeter() {
        //Centimeter to Kilometer
        double  valueSelector1 =  (userValue1 /100000);
        String _valueSelector1 = decimalScientificFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Centimeter to Meter
        double valueSelector2 =  (userValue1 /100);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Centimeter to Centimeter
        int valueSelector3 = (int) (userValue1 * 1);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Centimeter to Millimeter
        int valueSelector4 = (int) (userValue1 * 10);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Centimeter to Micrometer
        int valueSelector5 = (int) (userValue1 *  10000);
        String _valueSelector5 = decimalFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Centimeter to Nanometer
        double valueSelector6 =  (userValue1 *  1e+7);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Centimeter to Mile
        double valueSelector7 = userValue1 * 6.2137e-6;
        String _valueSelector7 = decimalScientificFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Centimeter to Yard
        double valueSelector8 = userValue1 /91.44;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Centimeter to Foot
        double valueSelector9 =  (userValue1 /30.48);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Centimeter to inch
        double valueSelector10 =  (userValue1 /2.54);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Centimeter to Nautical mile
        double valueSelector11 = userValue1 *5.3996e-6 ;
        String _valueSelector11 = decimalScientificFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Millimeter() {
        //Millimeter to Kilometer
        double  valueSelector1 =  (userValue1 /1e+6);
        String _valueSelector1 = decimalScientificFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Millimeter to Meter
        double valueSelector2 =  (userValue1 /1000);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Millimeter to Centimeter
        double valueSelector3 =  (userValue1 /10);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Millimeter to Millimeter
        int valueSelector4 = (int) (userValue1 * 1);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Millimeter to Micrometer
        int valueSelector5 = (int) (userValue1 *  1000);
        String _valueSelector5 = decimalFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Millimeter to Nanometer
        double valueSelector6 =  (userValue1 *  1e+6);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Millimeter to Mile
        double valueSelector7 = userValue1 * 6.2137e-7;
        String _valueSelector7 = decimalScientificFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Millimeter to Yard
        double valueSelector8 = userValue1 /914;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Millimeter to Foot
        double valueSelector9 =  (userValue1 / 305);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Millimeter to inch
        double valueSelector10 =  (userValue1 /25.4);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Millimeter to Nautical mile
        double valueSelector11 = userValue1 * 5.3996e-7;
        String _valueSelector11 = decimalScientificFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Micrometer() {
        //Micrometer to Kilometer
        double  valueSelector1 =  (userValue1 / 1e+9);
        String _valueSelector1 = decimalScientificFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Micrometer to Meter
        double valueSelector2 =  (userValue1 / 1e+6);
        String _valueSelector2 = decimalScientificFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Micrometer to Centimeter
        double valueSelector3 =  (userValue1 /10000);
        String _valueSelector3 = decimalScientificFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Micrometer to Millimeter
        double valueSelector4 =  (userValue1/1000);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Micrometer to Micrometer
        int valueSelector5 = (int) (userValue1 *  1);
        String _valueSelector5 = decimalFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Micrometer to Nanometer
        int valueSelector6 = (int) (userValue1 *  1000);
        String _valueSelector6 = decimalFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Micrometer to Mile
        double valueSelector7 = userValue1 * 6.2137e-10;
        String _valueSelector7 = decimalScientificFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Micrometer to Yard
        double valueSelector8 = userValue1 * 1.0936e-6;
        String _valueSelector8 = decimalScientificFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Micrometer to Foot
        double valueSelector9 =  (userValue1 * 3.2808e-6);
        String _valueSelector9 = decimalScientificFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Micrometer to inch
        double valueSelector10 =  (userValue1 * 3.937e-5);
        String _valueSelector10 = decimalScientificFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Micrometer to Nautical mile
        double valueSelector11 = userValue1 * 5.3996e-10;
        String _valueSelector11 = decimalScientificFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Nanometer() {
        //Nanometer to Kilometer
        double  valueSelector1 =  (userValue1 /1e+12);
        String _valueSelector1 = decimalScientificFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Nanometer to Meter
        double valueSelector2 =  (userValue1 /1e+9);
        String _valueSelector2 = decimalScientificFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Nanometer to Centimeter
        double valueSelector3 =  (userValue1 /1e+7);
        String _valueSelector3 = decimalScientificFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Nanometer to Millimeter
        double valueSelector4 =  (userValue1 /1e+6);
        String _valueSelector4 = decimalScientificFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Nanometer to Micrometer
        double valueSelector5 = userValue1 /1000;
        String _valueSelector5 = decimalFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Nanometer to Nanometer
        int valueSelector6 = (int) (userValue1 *  1);
        String _valueSelector6 = decimalFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Nanometer to Mile
        double valueSelector7 = userValue1 * 6.2137e-13;
        String _valueSelector7 = decimalScientificFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Nanometer to Yard
        double valueSelector8 = userValue1 * 1.0936e-9;
        String _valueSelector8 = decimalScientificFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Nanometer to Foot
        double valueSelector9 =  (userValue1 * 3.2808e-9);
        String _valueSelector9 = decimalScientificFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Nanometer to inch
        double valueSelector10 = (userValue1 * 3.937e-8);
        String _valueSelector10 = decimalScientificFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Nanometer to Nautical mile
        double valueSelector11 = userValue1 * 5.3996e-13;
        String _valueSelector11 = decimalScientificFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Mile() {
        //Mile to Kilometer
        double  valueSelector1 = (userValue1 * 1.60934);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Mile to Meter
        double valueSelector2 =  (userValue1 * 1609.34);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Mile to Centimeter
        double valueSelector3 =  (userValue1 * 160934.4);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Mile to Millimeter
        double valueSelector4 = (userValue1 * 1.609e+6);
        String _valueSelector4 = decimalScientificFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Mile to Micrometer
        double valueSelector5 = userValue1 *  1.609e+9;
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Mile to Nanometer
        double valueSelector6 =  (userValue1 * 1.609e+12);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Mile to Mile
        int valueSelector7 = (int) (userValue1 * 1);
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Mile to Yard
        int valueSelector8 = (int) (userValue1 * 1760);
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Mile to Foot
        int valueSelector9 = (int) (userValue1 * 5280);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Mile to inch
        int valueSelector10 = (int) (userValue1 * 63360);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Mile to Nautical mile
        double valueSelector11 = userValue1 /1.151;
        String _valueSelector11 = decimalFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Yard() {
        //Yard to Kilometer
        double  valueSelector1 =  (userValue1 / 1094);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Yard to Meter
        double valueSelector2 =  (userValue1 /1.094);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Yard to Centimeter
        double valueSelector3 =  (userValue1 * 91.44);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Yard to Millimeter
        double valueSelector4 =  (userValue1 * 914.4);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Yard to Micrometer
        double valueSelector5 = userValue1 *  914400;
        String _valueSelector5 = decimalFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Yard to Nanometer
        double valueSelector6 =  (userValue1 *  9.144e+8);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Yard to Mile
        double valueSelector7 = userValue1 /1760;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Yard to Yard
        int valueSelector8 = (int) (userValue1 * 1);
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Yard to Foot
        int valueSelector9 = (int) (userValue1 * 3);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Yard to inch
        int valueSelector10 = (int) (userValue1 * 36);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Yard to Nautical mile
        double valueSelector11 = userValue1 /2025;
        String _valueSelector11 = decimalFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Foot() {
        //Foot to Kilometer
        double  valueSelector1 =  (userValue1 /3281);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Foot to Meter
        double valueSelector2 =  (userValue1 /3.281);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Foot to Centimeter
        double valueSelector3 =  (userValue1 * 30.48);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Foot to Millimeter
        double valueSelector4 =  (userValue1 * 304.8);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Foot to Micrometer
        int valueSelector5 = (int) (userValue1 *  304800);
        String _valueSelector5 = decimalFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Foot to Nanometer
        double valueSelector6 =  (userValue1 *  3.048e+8);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Foot to Mile
        double valueSelector7 = userValue1 /5280;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Foot to Yard
        double valueSelector8 = userValue1 /3;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Foot to Foot
        int valueSelector9 = (int) (userValue1 * 1);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Foot to inch
        int valueSelector10 = (int) (userValue1 * 12);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Foot to Nautical mile
        double valueSelector11 = userValue1 /6076;
        String _valueSelector11 = decimalFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Inch() {
        //inch to Kilometer
        double valueSelector1 = (userValue1 *2.54e-5);
        String _valueSelector1 = decimalScientificFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //inch to Meter
        double valueSelector2 =  (userValue1 /39.37);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //inch to Centimeter
        double valueSelector3 =  (userValue1 * 2.54);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //inch to Millimeter
        double valueSelector4 =  (userValue1 * 25.4);
        String _valueSelector4 = decimalFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //inch to Micrometer
        int valueSelector5 = (int) (userValue1 *  25400);
        String _valueSelector5 = decimalFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //inch to Nanometer
        double valueSelector6 =  (userValue1 *  2.54e+7);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //inch to Mile
        double valueSelector7 = userValue1 * 1.5783e-5;
        String _valueSelector7 = decimalScientificFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //inch to Yard
        double valueSelector8 = userValue1/36;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //inch to Foot
        double valueSelector9 =  (userValue1 /12);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //inch to inch
        int valueSelector10 = (int) (userValue1 * 1);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //inch to Nautical mile
        double valueSelector11 = userValue1 * 1.3715e-5;
        String _valueSelector11 = decimalScientificFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
    }

    private void update_Nautical_Mile() {
        //Nautical_mile to Kilometer
        double  valueSelector1 =  (userValue1 * 1.852);
        String _valueSelector1 = decimalFormat.format(valueSelector1);
        result1.setText(_valueSelector1);
        //Nautical_mile to Meter
        int valueSelector2 = (int) (userValue1 * 1852);
        String _valueSelector2 = decimalFormat.format(valueSelector2);
        result2.setText(_valueSelector2);
        //Nautical_mile to Centimeter
        int valueSelector3 = (int) (userValue1 * 185200);
        String _valueSelector3 = decimalFormat.format(valueSelector3);
        result3.setText(_valueSelector3);
        //Nautical_mile to Millimeter
        double valueSelector4 =  (userValue1 * 1.852e+6);
        String _valueSelector4 = decimalScientificFormat.format(valueSelector4);
        result4.setText(_valueSelector4);
        //Nautical_mile to Micrometer
        double valueSelector5 = userValue1 *  1.852e+9;
        String _valueSelector5 = decimalScientificFormat.format(valueSelector5);
        result5.setText(_valueSelector5);
        //Nautical_mile to Nanometer
        double valueSelector6 =  (userValue1 *  1.852e+12);
        String _valueSelector6 = decimalScientificFormat.format(valueSelector6);
        result6.setText(_valueSelector6);
        //Nautical_mile to Mile
        double valueSelector7 = userValue1 * 1.15078;
        String _valueSelector7 = decimalFormat.format(valueSelector7);
        result7.setText(_valueSelector7);
        //Nautical_mile to Yard
        double valueSelector8 = userValue1 * 2025.37;
        String _valueSelector8 = decimalFormat.format(valueSelector8);
        result8.setText(_valueSelector8);
        //Nautical_mile to Foot
        double valueSelector9 = (userValue1 * 6076.12);
        String _valueSelector9 = decimalFormat.format(valueSelector9);
        result9.setText(_valueSelector9);
        //Nautical_mile to inch
        double valueSelector10 =  (userValue1 * 72913.4);
        String _valueSelector10 = decimalFormat.format(valueSelector10);
        result10.setText(_valueSelector10);
        //Nautical_mile to Nautical mile
        int valueSelector11 = (int) (userValue1 * 1);
        String _valueSelector11 = decimalFormat.format(valueSelector11);
        result11.setText(_valueSelector11);
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
        String activity_result11;
        activity_result11 = result11.getText().toString();
        String User_enter_data;
        User_enter_data = UserInput.getText().toString();

        // above declared string are exported to share activity
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody =  "Results based on your input for "+ User_enter_data +" "+selectedItem + "\n"
                + "Kilometer (km) : " + activity_result + "\n"
                + "Meter (m)" + activity_result2 + "\n"
                + "Centimeter (cm):" + activity_result3+ "\n"
                + "Millimeter (mm): " + activity_result4 + "\n"
                + "Micrometer (μm): " + activity_result5  + "\n"
                + " Nanometer (nm) :" + activity_result6+ "\n"
                + "Mile (mi): " + activity_result7 + "\n"
                + " Yard (yd)  : " + activity_result8  + "\n"
                + "Foot (ft):" + activity_result9+ "\n"
                + "Inch (in) : " + activity_result10 + "\n"
                + "  Nautical Mile (NM) : " + activity_result11  + ".\n";

        String shareSub = "Length Converter Results Powered by Transpose Solutions Android App - Unit Converter ";
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