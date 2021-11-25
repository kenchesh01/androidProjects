package com.transposesolutions.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


//import com.example.currency_converter.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



public class CurrencyModule1 extends AppCompatActivity {
   Spinner selectorFrom,selectorTo;
   EditText mUserInput;
   TextView result1;
   int x=0,y=0;
    double userValue1 = 0;
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
            if (editable.length() == 0) {
                result1.setText("");

            } else {
                checkForChanges1();
            }
        }
    };
    private void checkForChanges1() {
        // First get the edit text value from the activity
        String activityTextValue1 = mUserInput.getText().toString();
        // convert double
        try {
            userValue1 = Double.parseDouble(activityTextValue1);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String spinnerValue = String.valueOf(selectorFrom.getSelectedItem());
        switch (spinnerValue) {
            case "USD – US Dollar":
                x = 1;
                break;
            case "CAD – Canadian Dollar":
                x = 2;
                break;
            case "INR – Indian rupee":
                x = 3;
                break;
            default:
                break;
        }
        String spinnerValue2 = String.valueOf(selectorTo.getSelectedItem());
        switch (spinnerValue2) {
            case "INR – Indian rupee":
                y = 1;
                break;
            case "CAD – Canadian Dollar":
                y = 2;
                break;
            case "USD – US Dollar":
                y = 3;
                break;
            default:
                break;
        }

        if ((x == 1) &&(y==1)){
            update_usd_To_ind();
        }
    }

    private void update_usd_To_ind() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_module1);
        selectorFrom = findViewById(R.id.selector_1);
        selectorTo = findViewById(R.id.selector_2);
        mUserInput = findViewById(R.id.user_input);
        result1 =findViewById(R.id.result);
        // to perform the default logic on create
        int defaultValue1 = 100;
        String displayValue1 = String.valueOf(defaultValue1);
        mUserInput.setText(displayValue1);
        // set listeners
        mUserInput.addTextChangedListener(mTextWatcher);
        checkForChanges1();
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_currency_items, R.layout.spinner_selector_items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Apply the adapter to the spinner
        selectorFrom.setAdapter(adapter);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinner_currency_items1, R.layout.spinner_selector_items);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown);
        // Apply the adapter to the spinner
        selectorTo.setAdapter(adapter2);

    }}