package com.transposesolutions.justnumbers;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Random;

public class SubtractionModule1 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;

    // declare Button EditText, and TextView Widgets. Import the required class
    TextView mUserInput, result, ScoreDisplay,topAddend, bottomAddend, clock, displayLength;
    Button key1, key2, key3, key4, key5, key6, key7, key8, key9, key0, keyClear;
    ImageButton checkAnswer;
    // database code
    public static MyAppDataBase myAppDataBase;
    UserDataTable userDataTable = new UserDataTable();
    UserDataTable[] retryQuestions;
    int retryMathProblems = 0;
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
        setContentView(R.layout.activity_subtraction_module1);

        //Database
        myAppDataBase =  MyAppDataBase.getDbInstance(this.getApplicationContext());
        // Load Navigation View.
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Declare the UI elements
        ScoreDisplay=findViewById(R.id.Score);
        result = findViewById(R.id.result);
        clock = findViewById(R.id.timer);
        displayLength = findViewById(R.id.display_questions);
        topAddend = findViewById(R.id.top_addend);
        bottomAddend = findViewById(R.id.bottom_addend);
        key1 = findViewById(R.id.key_1);
        key2 = findViewById(R.id.key_2);
        key3 = findViewById(R.id.key_3);
        key4 = findViewById(R.id.key_4);
        key5 = findViewById(R.id.key_5);
        key6 = findViewById(R.id.key_6);
        key7 = findViewById(R.id.key_7);
        key8 = findViewById(R.id.key_8);
        key9 = findViewById(R.id.key_9);
        key0 = findViewById(R.id.key_0);
        keyClear = findViewById(R.id.key_clear);
        mUserInput = findViewById(R.id.user_input);
        checkAnswer = findViewById(R.id.key_send);

        // set listeners
        mUserInput.addTextChangedListener(mTextWatcher);
        // run once to disable if empty
        checkFieldsForEmptyValues();
        // Method called to initiate the clock
        handler = new Handler() ;

        // AppTracker Class to store/retrieve data
        AppTracker appTracker = (AppTracker)getApplicationContext();
        // Set activity length based on the default value or user selected values
        int mMathProblems = appTracker.getTotalMathProblems();
        if(mMathProblems==1){
            appTracker.setNumberOfQuestions(20);
        }else if(mMathProblems==2) {
            appTracker.setNumberOfQuestions(50);
        }else if(mMathProblems==3) {
            appTracker.setNumberOfQuestions(75);
        }else if(mMathProblems==4) {
            appTracker.setNumberOfQuestions(100);
        }
        // To track and redo the mistakes
        Boolean mMistakeQuestions = appTracker.getMistakeQuestions();
        if(mMistakeQuestions){
            updateQuestion();
        }
        else {
            retryQuestions = myAppDataBase.myDao().retry();
            myAppDataBase.myDao().deleteTable1(userDataTable);
            upDateReTryQuestions(retryMathProblems);
        }
        //mClockSettings
        boolean mClock = appTracker.getClockSettings();
        if(mClock) {
            getClockTime();
        }
        clock.setText("");


        // On click event for Key Number 1
        key1.setOnClickListener(v -> {
            String mInput = mUserInput.getText().toString();
//            String answer = mInput + "1";
//            mUserInput.setText(answer);
            if (mInput.equals("0"))
            {
                mUserInput.setText("1");
            }
            else
            {
                String answer = mInput + "1";
                mUserInput.setText(answer);
            }

        });
        // On click event for Key Number 2
        key2.setOnClickListener(v -> {
            String mInput = mUserInput.getText().toString();
            if (mInput.equals("0"))
            {
                mUserInput.setText("2");
            }
            else
            {
                String answer = mInput + "2";
                mUserInput.setText(answer);
            }
        });
        // On click event for Key Number 3
        key3.setOnClickListener(v -> {
            String mInput = mUserInput.getText().toString();
            if (mInput.equals("0"))
            {
                mUserInput.setText("3");
            }
            else
            {
                String answer = mInput + "3";
                mUserInput.setText(answer);
            }
        });
        // On click event for Key four number
        key4.setOnClickListener(v -> {
            String mInput = mUserInput.getText().toString();
            if (mInput.equals("0"))
            {
                mUserInput.setText("4");
            }
            else
            {
                String answer = mInput + "4";
                mUserInput.setText(answer);
            }
        });
        // On click event for Key Number 5
        key5.setOnClickListener(v -> {
            String mInput = mUserInput.getText().toString();
            if (mInput.equals("0"))
            {
                mUserInput.setText("5");
            }
            else
            {
                String answer = mInput + "5";
                mUserInput.setText(answer);
            }
        });
        // On click event for Key Number 6
        key6.setOnClickListener(v -> {
            String mInput = mUserInput.getText().toString();
            if (mInput.equals("0"))
            {
                mUserInput.setText("6");
            }
            else
            {
                String answer = mInput + "6";
                mUserInput.setText(answer);
            }
        });
        // On click event for Key Number 7
        key7.setOnClickListener(v -> {
            String mInput = mUserInput.getText().toString();
            if (mInput.equals("0"))
            {
                mUserInput.setText("7");
            }
            else
            {
                String answer = mInput + "7";
                mUserInput.setText(answer);
            }
        });
        // On click event for Key Number 8
        key8.setOnClickListener(v -> {
            String mInput = mUserInput.getText().toString();
            if (mInput.equals("0"))
            {
                mUserInput.setText("8");
            }
            else
            {
                String answer = mInput + "8";
                mUserInput.setText(answer);
            }
        });
        // On click event for Key Number 9
        key9.setOnClickListener(v -> {
            String mInput = mUserInput.getText().toString();
            if (mInput.equals("0"))
            {
                mUserInput.setText("9");
            }

            else
            {
                String answer = mInput + "9";
                mUserInput.setText(answer);
            }
        });
        // On click event for Key clear
        keyClear.setOnClickListener(v -> {
            mUserInput.setText("");
            mUserInput.clearFocus();
        });
        // On click event for Key Number Zero
        key0.setOnClickListener(v ->{
            String mInput = mUserInput.getText().toString();
            if (mInput.equals("0"))
            {
                mUserInput.setText("0");
            }
            else
            {
                String answer = mInput + "0";
                mUserInput.setText(answer);
            }
        });

        // display activity length
        int NumberOfQns =appTracker.getNumberOfQuestions();
        // print the number of Questions like 1 of 20, 2 of 20.
        String totalQuestions =String.valueOf(NumberOfQns);
        String QuestionNumber = String.valueOf(QuestionNumbers);
        String PrintQn = (QuestionNumber+" of "+totalQuestions);
        displayLength.setText(PrintQn);

        // when user enters the answer and taps the check button,
        // perform some function to validate the user input and update score and data to text views
        checkAnswer.setOnClickListener(view -> {
            Qn++;
            QuestionNumbers++;
            if(count<NumberOfQns){
                String totalQuestions1 =String.valueOf(NumberOfQns);
                String QuestionNumber1 = String.valueOf(QuestionNumbers);
                String PrintQn1 = (QuestionNumber1+" of "+totalQuestions1);
                displayLength.setText(PrintQn1);
                updateGame();
                if(mMistakeQuestions){
                    updateQuestion();
                }else{
                    upDateReTryQuestions(retryMathProblems);
                }
            }else {
                updateGame();
                stopClock();
                clock = findViewById(R.id.timer);
                String getTime = clock.getText().toString();
                Intent intent = new Intent(SubtractionModule1.this, SubtractionModule1Result.class);
                Bundle bundle = new Bundle();
                bundle.putString(GAME_MESSAGE1, String.valueOf(gameScore));
                bundle.putString(GAME_MESSAGE2, getTime );
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //Generate random number and assign the value to Top Addends and Bottom Addends
    private void  updateQuestion() {
        Random random = new Random(); //instance of random class
        num1 = 10 + random.nextInt(81);
        num2 = random.nextInt(10);
        String topAddends = Integer.toString(num1);
        topAddend.setText(topAddends);
        String bottomAddends = Integer.toString(num2);
        bottomAddend.setText(bottomAddends);
        mUserInput.setText("");
    }

    //game logic - method called verify the user input, update score and load new set of math problems to the activity
    private void updateGame() {
        String input = mUserInput.getText().toString();
        userDataTable.setUserInput(Integer.parseInt(input));
        // check the answer is  correct or wrong
        if (gameValidation().equals(input)) {
            result.setText(R.string.correct_answer);
            //
            String checkedMark = "\u2714";
            userDataTable.setStatus(checkedMark);
            // AppTracker Class to store/retrieve data
            AppTracker appTracker = (AppTracker)getApplicationContext();
            int NumberOfQns =appTracker.getNumberOfQuestions();
            String s = String.valueOf(NumberOfQns);
            gameScore++;
            ScoreDisplay.setText(MessageFormat.format("{0}{1}", gameScore,  "/"+ s));
        } else {
            // AppTracker Class to store/retrieve data
            AppTracker appTracker = (AppTracker)getApplicationContext();
            int NumberOfQns =appTracker.getNumberOfQuestions();
            String s = String.valueOf(NumberOfQns);
            String checkedMark = "\u2718";
            userDataTable.setStatus(checkedMark);
            result.setText(MessageFormat.format("{0}{1}", "Sorry! Correct answer is ", gameValidation()));
            ScoreDisplay.setText(MessageFormat.format("{0}{1}", gameScore, "/"+s));
        }
        userDataTable.setId(Qn);
        userDataTable.setBottomAddend(num2);
        userDataTable.setTopAddend(num1);
        Answer = Integer.parseInt(correctAnswer);
        userDataTable.setAnswer(Answer);
        myAppDataBase.myDao().addUsers(userDataTable);
        count++;
    }

    //to validate the user input, add top addend (num1) + bottom addend (num2)
    private String gameValidation() {
        int sumAddend = num1 - num2;
        correctAnswer = String.valueOf(sumAddend);
        return correctAnswer;
    }

    private void upDateReTryQuestions(int Qns) {
        // count the mistake problems
        int size = retryQuestions.length;
        if(Qns<size) {
            AppTracker appTracker = (AppTracker) getApplicationContext();
            appTracker.setNumberOfQuestions(size);
            UserDataTable  userDataTable1 = retryQuestions[Qns];
            num1 = userDataTable1.getTopAddend();
            num2 = userDataTable1.getBottomAddend();
            String topAddends = Integer.toString(num1);
            topAddend.setText(topAddends);
            String bottomAddends = Integer.toString(num2);
            bottomAddend.setText(bottomAddends);
            mUserInput.setText("");
            retryMathProblems++;
        }
    }
    // Method to pause the clock
    private void stopClock() {
        TimeBuff += MillisecondTime;
        handler.removeCallbacks(runnable);
    }
    // Method to initiate clock
    private void getClockTime() {
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }
    // Method to handle the clock running
    public Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            int seconds = (int) (UpdateTime / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            String masterTime = ("" + minutes + ":" + String.format(Locale.getDefault(),"%02d", seconds));
            clock.setText(masterTime);
            handler.postDelayed(this, 0);
        }
    };

    // override method to responsible for responding correctly to the items specified in the menu resource file.
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // override method to listen for any click events on selecting a particular item from the drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // AppTracker Class to store/retrieve data
        AppTracker appTracker = (AppTracker)getApplicationContext();
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.main_home) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.addition) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, Addition.class);
            startActivity(intent);
        } else if (id == R.id.subtraction) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, Subtraction.class);
            startActivity(intent);
        } else if (id == R.id.multiplication) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, Multiplication.class);
            startActivity(intent);
        } else if (id == R.id.division) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, Division.class);
            startActivity(intent);
        } else if (id == R.id.Settings) {
            appTracker.setMistakeQuestions(true);
            Intent intent = new Intent(this, MyAppSettings.class);
            startActivity(intent);
        } else if (id == R.id.nav_rate) {
            try {
                appTracker.setMistakeQuestions(true);
                Uri uri1 = Uri.parse("market://details?id=" + getPackageName());
                Intent gotoMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket1);
            } catch (ActivityNotFoundException e){
                Uri uri1 = Uri.parse("http://play.google.com/store/apps/details/id=" + getPackageName());
                Intent gotoMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket1);
            }
        } else if (id == R.id.nav_share) {
            appTracker.setMistakeQuestions(true);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Check out this fun math app! Math app to practice addition, subtraction, multiplication or division." +  "\n"+  "\n"
                    + "Google Play store:" +  "\n" +"https://play.google.com/store/apps/details?id=com.transposesolutions.justnumbers&hl=en" + "\n";
            String shareSub = "Check out this fun math app from Transpose Solutions";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        } else if (id == R.id.nav_apps) {
            try {
                appTracker.setMistakeQuestions(true);
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

    @Override
    public void onBackPressed() {
        AppTracker appTracker = (AppTracker) getApplicationContext();
        Boolean mMistakeQuestions = appTracker.getMistakeQuestions();
        if(mMistakeQuestions){
            super.onBackPressed();
            finish();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(SubtractionModule1.this);
            builder.setMessage("Are you sure want to exit? If you exit, you will not be able to return back to redo current mistake problem set?");
            builder.setCancelable(true);
            builder.setPositiveButton("Yes", (dialog, id) -> {
                AppTracker appTracker1 = (AppTracker) getApplicationContext();
                appTracker1.setMistakeQuestions(true);
                myAppDataBase.myDao().deleteTable1(userDataTable);
                Intent intent = new Intent(SubtractionModule1.this, Subtraction.class);
                startActivity(intent);
            });
            builder.setNegativeButton("No", (dialog, id) -> dialog.cancel());
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

}