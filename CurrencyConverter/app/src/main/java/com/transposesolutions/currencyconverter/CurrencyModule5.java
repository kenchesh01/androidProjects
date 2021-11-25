package com.transposesolutions.currencyconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CurrencyModule5 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private AdView adView;
    private ActionBarDrawerToggle mToggle;
    EditText UserInput;
    Spinner selector_1;
    String selectedItem,conversionValue;
    double x=0;
    double userValue1=0;
    int tableRows = 0;
    int customCountriesZone =1;
    List<Double> dataCurrency = new ArrayList<Double>();
    String[] mSpinnerMajorsSize = {"INR","USD","EUR","JPY","GBP","CHF","AUD","CAD"};
    String[] mSpinnerALLSize = {"AED","AFN","ALL","AMD","AOA","ARS","AUD","CAD","CNY","EUP","INR","ZMW"};


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

                checkForChanges1();

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_module5);
        //UI elements
        UserInput = findViewById(R.id.user_input);
        selector_1 = findViewById(R.id.selector_1);
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        // to perform the default logic on create
        int defaultValue1 = 1;
        String displayValue1 = String.valueOf(defaultValue1);
        UserInput.setText(displayValue1);
        // set listeners
        UserInput.addTextChangedListener(mTextWatcher);
        checkForChanges1();


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
        // AppTracker Class to store/retrieve data
        MyApplication appTracker = (MyApplication) getApplicationContext();
        // Set activity length based on the default value or user selected values
        int zone = appTracker.getCustomCountriesZone();
        // Create an ArrayAdapter using the string array and a default spinner layout
        if(zone==1) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.spinner_majors_country, R.layout.spinner_selector_items);
            adapter.setDropDownViewResource(R.layout.spinner_dropdown);
            // Apply the adapter to the spinner
            selector_1.setAdapter(adapter);

        }else if(zone==2){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.spinner_All_country, R.layout.spinner_selector_items);
            adapter.setDropDownViewResource(R.layout.spinner_dropdown);
            // Apply the adapter to the spinner
            selector_1.setAdapter(adapter);

        }
        // Specify the layout to use when the list of choices appears


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
                if (position == 0) {
                    update_INR_to_all();
                } else if (position == 1) {
                    update_USD_to_all();
                } else if (position == 2) {
                    update_EUR_to_all();
                } else if (position == 3) {
                    update_JPY_to_all();
                }else if (position == 4) {
                    update_GBP_to_all();
                } else if (position == 5) {
                    update_CHF_to_all();
                } else if (position == 6) {
                    update_AUD_to_all();
                } else if (position == 7) {
                    update_CAD_to_all();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        String[] columns = new String[3];
        // Add table rows to the table
        this.addTableRow(tableLayout, columns);
        if(zone==1){
            for (String str : mSpinnerMajorsSize) {
                String[] _DisplayData = new String[3];
                for(Double currency:dataCurrency){
                    // data for column-1
                    double value1 = currency;
                    _DisplayData[0] = String.valueOf(value1);
                    System.out.println(value1);
                }


                String country = str;
                _DisplayData[1] = str;
                System.out.println(country);
                // data for column-3
                String image = " no image";
                _DisplayData[2] = image;
                System.out.println(image);
                addTableRow(tableLayout, _DisplayData);


            }
        }else if(zone==2)
        for (String str : mSpinnerALLSize) {
            String[] _DisplayData = new String[3];
                               for(Double currency:dataCurrency){
                                   // data for column-1
                                   double value1 = currency;
                                   _DisplayData[0] = String.valueOf(value1);
                                   System.out.println(value1);
                               }


            String country = str;
            _DisplayData[1] = str;
            System.out.println(country);
            // data for column-3
            String image = " no image";
            _DisplayData[2] = image;
            System.out.println(image);
            addTableRow(tableLayout, _DisplayData);


        }
}

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

    private void checkForChanges1() {
        // First get the edit text value from the activity
        String activityTextValue1 = UserInput.getText().toString();
        // convert double
        try {
            userValue1 = Double.parseDouble(activityTextValue1);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String spinnerValue = String.valueOf(selector_1.getSelectedItem());
        switch (spinnerValue) {
            case "INR-Indian Rupee":
                x = 1;
                break;
            case "USD-United States Dollar":
                x = 2;
                break;
            case "EUR-Euro":
                x = 3;
                break;
            case "JPY-Japanese Yen":
                x = 4;
                break;
            case "GBP-British Pound Sterling":
                x = 5;
                break;
            case "CHF-Swiss Franc":
                x = 6;
                break;
            case "AUD-Australian Dollar":
                x = 7;
                break;
            case "CAD-Canadian Dollar":
                x = 8;
                break;
            default:
                break;
        }
        if (x == 0) {
            update_INR_to_all();
        } else if (x == 1) {
            update_USD_to_all();
        } else if (x == 2) {
            update_EUR_to_all();
        } else if (x == 3) {
            update_JPY_to_all();
        }else if (x == 4) {
            update_GBP_to_all();
        } else if (x == 5) {
            update_CHF_to_all();
        } else if (x == 6) {
            update_AUD_to_all();
        } else if (x == 7) {
            update_CAD_to_all();
        }
    }
    private void update_CAD_to_all() {
    }

    private void update_AUD_to_all() {
    }

    private void update_CHF_to_all() {
    }

    private void update_GBP_to_all() {
    }

    private void update_JPY_to_all() {
    }

    private void update_EUR_to_all() {
    }

    private void update_USD_to_all() {
    }

    private void update_INR_to_all() {

        String convertFromValue = "INR";
        String convertTO = "CAD";
        String japan = "JPY";

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://free.currconv.com/api/v7/convert?q="+convertFromValue+"_"+convertTO+"&compact=ultra&apiKey=c344be763a9c4cd858df";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Double ConversionRateValue = round(((Double) jsonObject.get(convertFromValue + "_" +convertTO)), 2);
                    dataCurrency.add(ConversionRateValue);
                    conversionValue = "" + round((ConversionRateValue * userValue1), 2);
                    String values = String.valueOf(conversionValue);
                    Log.d("Log",values);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
        String url2 = "https://free.currconv.com/api/v7/convert?q="+convertFromValue+"_"+japan+"&compact=ultra&apiKey=c344be763a9c4cd858df";
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Double ConversionRateValue = round(((Double) jsonObject.get(convertFromValue + "_" +japan)), 2);
                    conversionValue = "" + round((ConversionRateValue * userValue1), 2);
                    dataCurrency.add(ConversionRateValue);
                    String values = String.valueOf(conversionValue);
                    Log.d("Log",values);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest1);
        }
    private Double round(Double aDouble, int i) {
        if(i<0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(aDouble);
        bd = bd.setScale(i, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
        if (item.getItemId() == R.id.majors) {
            updateMajors();
return true;
        }else  // toolbar onOptionItemSelected
            if (item.getItemId() == R.id.mAll) {
                updateAll();
                return true;
            }
        return super.onOptionsItemSelected(item);
    }

    private void updateAll() {
        MyApplication appTracker = (MyApplication) getApplicationContext();
        // Set activity length based on the default value or user selected values
        appTracker.setCustomCountriesZone(2);
        // Set activity length based on the default value or user selected values
        startActivity(new Intent(CurrencyModule5.this,CurrencyModule5.class));

    }

    private void updateMajors() {
        MyApplication appTracker = (MyApplication) getApplicationContext();
        // Set activity length based on the default value or user selected values
        appTracker.setCustomCountriesZone(1);
        startActivity(new Intent(CurrencyModule5.this,CurrencyModule5.class));
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
        } else if (id == R.id.menu_1) {
            Intent intent = new Intent(this, CurrencyModule2.class);
            startActivity(intent);
        } else if (id == R.id.menu_2) {
            Intent intent = new Intent(this, CurrencyModule4.class);
            startActivity(intent);
        } else if (id == R.id.menu_3) {
            Intent intent = new Intent(this, CurrencyModule5.class);
            startActivity(intent);
        }
        return true;
    }
}