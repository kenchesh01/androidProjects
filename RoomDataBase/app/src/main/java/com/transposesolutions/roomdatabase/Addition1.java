package com.transposesolutions.roomdatabase;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Addition1 extends AppCompatActivity {

    // declare Button EditText, and TextView Widgets. Import the required class
    TextView mUserInput, result, ScoreDisplay,topAddend, bottomAddend, clock, displayLength;
    Button key1, key2, key3, key4, key5, key6, key7, key8, key9, key0, keyClear;
    ImageButton checkAnswer;


    int count=1, gameScore = 0, num1,num2,  QuestionNumbers=1, Qn =0;
    int Answer = 0;
    String correctAnswer;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;

    // declare package prefix , GAME_MESSAGE constant and key values
    public final static String GAME_MESSAGE1 = "com.transposesolutions.justnumbers.SCORE";
    public final static String GAME_MESSAGE2 = "com.transposesolutions.justnumbers.TIME";
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
        String s1 = mUserInput.getText().toString();
        checkAnswer.setEnabled(!s1.equals("") );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition1);
    }
}