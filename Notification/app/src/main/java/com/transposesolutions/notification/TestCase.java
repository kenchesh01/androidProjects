package com.transposesolutions.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TestCase extends AppCompatActivity {
EditText input1,input2;
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
    // check Fields For Empty Values
    private void checkFieldsForEmptyValues() {
        String s1 = input1.getText().toString();
        input2.setText(s1) ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_case);

        input1=findViewById(R.id.user_input1);
        input2=findViewById(R.id.user_input2);
//        // to perform the default logic on create
//        double defaultValue1 = 1;
//        String displayValue1 = String.valueOf(defaultValue1);
//        double defaultValue2 = 0.62;
//        String displayValue2 = String.valueOf(defaultValue2);
//        //
//        input1.setText(displayValue1);
//        input2.setText(displayValue2);
//        onLoad();
        // set listeners
        input1.addTextChangedListener(mTextWatcher);
        input2.addTextChangedListener(mTextWatcher);
        // run once to disable if empty
        checkFieldsForEmptyValues();
        input1.setText("3");
        input2.setText("6");




    }

//    private void onLoad() {
//        // First get the edit text value from the activity
////        String activityTextValue1 =  input1.getText().toString();
////        String activityTextValue2 =  input2.getText().toString();
////
//
//
////        input2.addTextChangedListener(new TextWatcher() {
////
////            @Override
////            public void afterTextChanged(Editable s) {}
////
////            @Override
////            public void beforeTextChanged(CharSequence s, int start,
////                                          int count, int after) {
////            }
////
////            @Override
////            public void onTextChanged(CharSequence s, int start,
////                                      int before, int count) {
////                if(s.length() != 0)
////                    input1.setText("");
////            }
////        });
//    }
}