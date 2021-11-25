package com.transposesolutions.currencyconverter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;


public class CurrencyModule2 extends AppCompatActivity {
    TextView convertFrom,convertTo,conversionRate,indRup;
    EditText amountToConvert;
    ArrayList<String> arrayList;
    Dialog fromDialog;
    Dialog toDialog;
    Button conversionButton;
    String convertFromValue,convertToValue,conversionValue;
    String[] country ={"AFN",
            "ALL",
            "DZD",
            "USD",
            "EUR",
            "AOA",
            "XCD",
            "XCD",
            "ARS",
            "AMD",
            "AWG",
            "AUD",
            "EUR",
            "AZM",
            "BSD",
            "BHD",
            "BDT",
            "BBD",
            "BYR",
            "EUR",
            "BZD",
            "XOF",
            "BMD",
            "INR",
            "BTN",
            "BOB",
            "BOV",
            "BAM",
            "BWP",
            "NOK",
            "BRL",
            "USD",
            "BND",
            "BGN",
            "XOF",
            "BIF",
            "KHR",
            "XAF",
            "CAD",
            "CVE",
            "KYD",
            "XAF",
            "XAF",
            "CLP",
            "CLF",
            "CNY",
            "AUD",
            "AUD",
            "COP",
            "COU",
            "KMF",
            "XAF",
            "CDF",
            "NZD",
            "CRC",
            "XOF",
            "HRK",
            "CUP",
            "CYP",
            "CZK",
            "DKK",
            "DJF",
            "XCD",
            "DOP",
            "USD",
            "EGP",
            "SVC",
            "USD",
            "XAF",
            "ERN",
            "EEK",
            "ETB",
            "FKP",
            "DKK",
            "FJD",
            "EUR",
            "EUR",
            "EUR",
            "XPF",
            "EUR",
            "XAF",
            "GMD",
            "GEL",
            "EUR",
            "GHC",
            "GIP",
            "EUR",
            "DKK",
            "XCD",
            "EUR",
            "USD",
            "GTQ",
            "GNF",
            "GWP",
            "XOF",
            "GYD",
            "HTG",
            "AUD",
            "EUR",
            "HNL",
            "HKD",
            "HUF",
            "ISK",
            "INR",
            "IDR",
            "IRR",
            "IQD",
            "EUR",
            "ILS",
            "EUR",
            "JMD",
            "JPY",
            "JOD",
            "KZT",
            "KES",
            "AUD",
            "SDD",
            "SRD",
            "NOK",
            "SZL",
            "SEK",
            "CHF",
            "SYP",
            "TWD",
            "TJS",
            "TZS",
            "THB",
            "USD",
            "XOF",
            "TTD",
            "TND",
            "TRL",
            "TMM",
            "USD",
            "AUD",
            "UGX",
            "UAH",
            "AED",
            "GBP",
            "USD",
            "USS",
            "USN",
            "USD",
            "UYU",
            "UZS",
            "VUV",
            "VEB",
            "VND",
            "XPF",
            "MAD",
            "YER",
            "ZMK",
            "ZWD"
    }; //TODO
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_module2);
        convertFrom= findViewById(R.id.convert_from_dropdown_menu);
        convertTo = findViewById(R.id.convert_to_dropdown_menu);
        conversionButton = findViewById(R.id.conversionButton);
        conversionRate = findViewById(R.id.conversionResult);
        amountToConvert = findViewById(R.id.amountConvertEditText);
        indRup = findViewById(R.id.Result_4);

        convertFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDialog = new Dialog(CurrencyModule2.this);
                fromDialog.setContentView(R.layout.from_spinner);
//                fromDialog.getWindow().setLayout(650,800);
                fromDialog.show();

                EditText editText =fromDialog.findViewById(R.id.edit_text);
                ListView listView = fromDialog.findViewById(R.id.list_view);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CurrencyModule2.this, android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        convertFrom.setText(adapter.getItem(position));
                        fromDialog.dismiss();
                        convertFromValue =adapter.getItem(position);
                    }
                });
            }
        });

        convertTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDialog =new Dialog(CurrencyModule2.this);
                toDialog.setContentView(R.layout.to_spinner);
//                toDialog.getWindow().setLayout(800,1000);
                toDialog.show();

                EditText editText =toDialog.findViewById(R.id.edit_text);
                ListView listView = toDialog.findViewById(R.id.list_view);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CurrencyModule2.this, android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        convertTo.setText(adapter.getItem(position));
                        toDialog.dismiss();
                        convertToValue =adapter.getItem(position);
                    }
                });
            }
        });

        conversionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Double amountToConvert = Double.valueOf(CurrencyModule2.this.amountToConvert.getText().toString());
                    getConversionRate(convertFromValue,convertToValue,amountToConvert);
                    updateIndia(convertFromValue,amountToConvert);
                }catch (Exception e){
                }
            }
        });


    }

    private void updateIndia(String convertFromValue, Double amountToConvert) {
        String convertTO = "INR";

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://free.currconv.com/api/v7/convert?q="+convertFromValue+"_"+convertTO+"&compact=ultra&apiKey=c344be763a9c4cd858df";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Double ConversionRateValue = round(((Double) jsonObject.get(convertFromValue + "_" +convertTO)), 2);
                    conversionValue = "" + round((ConversionRateValue * amountToConvert), 2);
                    indRup.setText(conversionValue);
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



    private void getConversionRate(String convertFromValues, String convertToValues, Double amountToConverts) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://free.currconv.com/api/v7/convert?q="+convertFromValues+"_"+convertToValues+"&compact=ultra&apiKey=c344be763a9c4cd858df";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    Double ConversionRateValue = round(((Double) jsonObject.get(convertFromValues + "_" + convertToValues)), 2);
                    conversionValue = "" + round((ConversionRateValue * amountToConverts), 2);
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


