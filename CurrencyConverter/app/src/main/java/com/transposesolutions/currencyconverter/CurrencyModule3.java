package com.transposesolutions.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CurrencyModule3 extends AppCompatActivity {
TextView mResult;
EditText mToConvert;
RadioButton mDollar,Meuro;
RadioGroup mRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_module3);
        mResult = (TextView) findViewById(R.id.result);
        mToConvert = (EditText) findViewById(R.id.toConvert);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioG);
        mDollar = (RadioButton) findViewById(R.id.dollar);
        Meuro = (RadioButton) findViewById(R.id.euro);

        mToConvert.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                convertCurrentAmount();

            }
        });
        mRadioGroup
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        convertCurrentAmount();
                    }
                });

    }
    public void convertCurrentAmount() {
        double exchangeRate = -1;
        String exchangeSymbol = null;
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.dollar:
                mDollar.setChecked(true);
                Meuro.setChecked(false);
                exchangeRate = 3.76;
                exchangeSymbol = "$";


                break;

            case R.id.euro:
                mDollar.setChecked(false);
                Meuro.setChecked(true);
                exchangeRate = 5;
                exchangeSymbol = "€";

                break;
        }
        if (exchangeRate > 0 && exchangeSymbol != null) {

            Double stringtoint = Double
                    .valueOf(mToConvert.getText().toString());
            double result = stringtoint * exchangeRate;
            mResult.setText("" + exchangeSymbol + result);

        }
    }
}
