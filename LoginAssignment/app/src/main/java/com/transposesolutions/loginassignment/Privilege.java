package com.transposesolutions.loginassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class Privilege extends AppCompatActivity {
    TextView name,mail;
    Button sendEmail,imageSwitcher;
String Name,Mail;
    UserDataBase userDataBase;
    ProgressBar simpleProgressBar;
    int progress = 0;
    private static int SPLASH_TIME_OUT = 10000; // 10000ms equal to 10 sec

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privilege);
        userDataBase = UserDataBase.getUserDataBase(getApplicationContext());
        name = findViewById(R.id.name);
        mail= findViewById(R.id.userMail);
        sendEmail = findViewById(R.id.sendMail);
        imageSwitcher = findViewById(R.id.image_switch);
        simpleProgressBar = findViewById(R.id.progressBar);

        List<UserEntity> test  = userDataBase.userDao().getUsersList();
        for(UserEntity userEntity :test){
             Name = userEntity.getName();
             Mail = userEntity.getEmail();
        }

        String userName = ("Hi ,"+Name);
        String userMail = ("Email : "+Mail);

        name.setText(userName);
        mail.setText(userMail);
//
//        UserDataBase userDataBase = UserDataBase.getUserDataBase(getApplicationContext());
//        final UserDao userDao = userDataBase.userDao();



        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (Privilege.this,Email.class);
                        startActivity(intent);
            }
        });
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call a function
                setProgressValue(progress);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        Intent i = new Intent(Privilege.this, ImageSwitcherDemo.class);
                        startActivity(i);
                        // close this activity
                        finish();
                    }
                }, SPLASH_TIME_OUT);
            }
        });
    }
    private void setProgressValue(final int progress) {
        simpleProgressBar.setVisibility(View.VISIBLE);
        // set the progress
        simpleProgressBar.setProgress(progress);
        // thread is used to change the progress value
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        setProgressValue(progress + 10);
                    }
                });
                thread.start();

    }
}