package com.transposesolutions.currencyconverter;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyMajorsCurrency extends AppCompatActivity {
    TextView result1,result2,result3,result4,result5,result6,result7,result8;
    private ActionBarDrawerToggle mToggle;
    EditText UserInput;
    Spinner selector_1;
    String selectedItem="USD",conversionValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_majors_currency);

        //UI elements
        UserInput = findViewById(R.id.user_input);
        selector_1 = findViewById(R.id.selector_1);
        result1 = findViewById(R.id.Result_1);
        result2 = findViewById(R.id.Result_2);
        result3 = findViewById(R.id.Result_3);
        result4 = findViewById(R.id.Result_4);
        result5 = findViewById(R.id.Result_5);
        result6 = findViewById(R.id.Result_6);
        result7 = findViewById(R.id.Result_7);
        result8 = findViewById(R.id.Result_8);

        // to perform the default logic on create
        double defaultValue1 = (double) 100;
        String displayValue1 = String.valueOf(defaultValue1);
        UserInput.setText(displayValue1);
        OnLoad();
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_majors_country, R.layout.spinner_selector_items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Apply the adapter to the spinner
        selector_1.setAdapter(adapter);
        selector_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) parent.getItemAtPosition(position).toString();
                OnLoad();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void OnLoad() {
        try{

            Double amountToConvert = Double.valueOf(CurrencyMajorsCurrency.this.UserInput.getText().toString());
            getConversionRate(selectedItem,amountToConvert);

        }catch (Exception e){
        }
    }

    private void getConversionRate(String selectedItem, Double amountToConvert) {
        String convertTO ="INR";
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://free.currconv.com/api/v7/convert?q="+selectedItem+"_"+convertTO+"&compact=ultra&apiKey=c344be763a9c4cd858df";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Double ConversionRateValue = round((Double) jsonObject.get(selectedItem + "_" +convertTO), 2);
                    conversionValue = "" + round((ConversionRateValue * amountToConvert), 2);
                    result2.setText(conversionValue);
                    Log.d("Log",conversionValue);
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
    }
    private Double round(Double aDouble, int i) {
        if(i<0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(aDouble);
        bd = bd.setScale(i, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}