package com.transposesolutions.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Currencytesting extends AppCompatActivity {
    TextView conversionRate;
    Spinner convertFrom,convertTo;
    EditText amountToConvert;
    Button conversionButton;
    String convertFromValue="USD",convertToValue="INR",conversionValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencytesting);
        convertFrom= findViewById(R.id.convert_from_dropdown_menu);
        convertTo = findViewById(R.id.convert_to_dropdown_menu);
        conversionButton = findViewById(R.id.conversionButton);
        conversionRate = findViewById(R.id.conversionResult);
        amountToConvert = findViewById(R.id.amountConvertEditText);

        // to perform the default logic on create
        int defaultValue1 = 100;
        String displayValue1 = String.valueOf(defaultValue1);
        amountToConvert.setText(displayValue1);
OnLoad();
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_currency_items, R.layout.spinner_selector_items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Apply the adapter to the spinner
        convertFrom.setAdapter(adapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.spinner_currency_items1, R.layout.spinner_selector_items);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown);
        // Apply the adapter to the spinner
        convertTo.setAdapter(adapter1);
        convertFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertFromValue = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        convertTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertToValue = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        conversionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnLoad();
            }
        });



    }

    private void OnLoad() {
        try{
            Double amountToConvert = Double.valueOf(Currencytesting.this.amountToConvert.getText().toString());
            getConversionRate(convertFromValue,convertToValue,amountToConvert);

        }catch (Exception e){
        }
    }

    private void getConversionRate(String convertFromValue, String convertToValue, Double amountToConvert) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://free.currconv.com/api/v7/convert?q="+convertFromValue+"_"+convertToValue+"&compact=ultra&apiKey=c344be763a9c4cd858df";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Double ConversionRateValue = round(((Double) jsonObject.get(convertFromValue + "_" + convertToValue)), 2);
                    conversionValue = "" + round((ConversionRateValue * amountToConvert), 2);
                    conversionRate.setText(conversionValue);
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