package com.transposesolutions.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CurrencyModule4 extends AppCompatActivity {


    EditText UserInput;
    ImageButton share;
    TextView result1, result2, result3;
    Spinner selector_1;
    double x = 0;
    double userValue1 = 0;
    String selectedItem;
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
                result2.setText("");
                result3.setText("");
            } else {
                checkForChanges1();
            }
        }
    };

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
            case "USD – US Dollar":
                x = 1;
                break;
            case "CAD – Canadian Dollar":
                x = 2;
                break;
            case "GBP - Pound Sterling":
                x = 3;
                break;
            default:
                break;
        }
        if (x == 1) {
            update_usd();
        } else if (x == 2) {
            update_cad();
        } else if (x == 3) {
            update_gbp();
        }
    }

    private void update_usd() {
        // usd to usd

        int valueSelector1 = (int) (userValue1 * 1);
        String _valueSelector1 = String.valueOf(valueSelector1);
        result1.setText(_valueSelector1);
        // usd  to can

        double valueSelector2 = (userValue1 * 1.24);
        String _valueSelector2 = String.valueOf(valueSelector2);
        result2.setText(_valueSelector2);
        //usd to gbp

        double valueSelector3 = (userValue1 *0.73);
        String _valueSelector3 = String.valueOf(valueSelector3);
        result3.setText(_valueSelector3);


    }

    private void update_cad() {
        // can to usd

        double valueSelector1 = (userValue1 *0.80);
        String _valueSelector1 = String.valueOf(valueSelector1);
        result1.setText(_valueSelector1);
        // cad to can

        int valueSelector2 = (int) (userValue1 * 1);
        String _valueSelector2 = String.valueOf(valueSelector2);
        result2.setText(_valueSelector2);
        //can to gbp

        double valueSelector3 = (userValue1 *0.58) ;
        String _valueSelector3 = String.valueOf(valueSelector3);
        result3.setText(_valueSelector3);


    }

    private void update_gbp() {
        // GBP to Usd

        double valueSelector1 = (userValue1 * 1.38);
        String _valueSelector1 = String.valueOf(valueSelector1);
        result1.setText(_valueSelector1);
        // GBP to can
        double valueSelector2 = (userValue1 * 1.77) ;
        String _valueSelector2 = String.valueOf(valueSelector2);
        result2.setText(_valueSelector2);
        //GBP to GBP

        int valueSelector3 = (int) (userValue1 * 1);
        String _valueSelector3 = String.valueOf(valueSelector3);
        result3.setText(_valueSelector3);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_module4);

        //UI elements
        UserInput = findViewById(R.id.user_input);
        selector_1 = findViewById(R.id.selector_1);
        result1 = findViewById(R.id.Result_1);
        result2 = findViewById(R.id.Result_2);
        result3 = findViewById(R.id.Result_3);
        share = findViewById(R.id.share);

        // to perform the default logic on create
        int defaultValue1 = 1;
        String displayValue1 = String.valueOf(defaultValue1);
        UserInput.setText(displayValue1);
        // set listeners
        UserInput.addTextChangedListener(mTextWatcher);
        checkForChanges1();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_currency_items, R.layout.spinner_selector_items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        // Apply the adapter to the spinner
        selector_1.setAdapter(adapter);
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
                    update_usd();
                } else if (position == 1) {
                    update_cad();
                } else if (position == 2) {
                    update_gbp();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        // onclick listener in share button
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // current activity string are copied to another set of string that are made available for the Share intent
                String activity_result;
                activity_result = result1.getText().toString();
                String activity_result2;
                activity_result2 = result2.getText().toString();
                String activity_result3;
                activity_result3 = result3.getText().toString();
                String User_enter_data;
                User_enter_data = UserInput.getText().toString();
                // above declared string are exported to share activity
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Based on your input for " + User_enter_data + " " + selectedItem + " find below the conversion: \n\n"
                        + "Celsius (C) : " + activity_result + "\n"
                        + "Fahrenheit (F) : " + activity_result2 + "\n"
                        + "Kelvin (K) :" + activity_result3 + "\n";
                String shareSub = "Your Unit Converter app by Transpose Solutions: Android App - Unit Converter ";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
    }
}