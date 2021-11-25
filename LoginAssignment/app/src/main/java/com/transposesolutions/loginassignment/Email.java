package com.transposesolutions.loginassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Properties;

public class Email extends AppCompatActivity {
    private EditText eTo,eSubject,eMsg;
    private Button button;
    UserDataBase userDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        eTo =  findViewById(R.id.txtTo);
        eSubject =  findViewById(R.id.txtSub);
        eMsg =  findViewById(R.id.txtMsg);
        button =  findViewById(R.id.btnSend);

        userDataBase = UserDataBase.getUserDataBase(getApplicationContext());
        List<UserEntity> test  = userDataBase.userDao().getUsersList();
        for(UserEntity userEntity :test){
           String  Mail = userEntity.getEmail();
            ((EditText) findViewById(R.id.txtTo)).setText(Mail);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }



    private void sendEmail() {

        try {
            GMailSender sender = new GMailSender("dev.transpose1002@gmail.com", "DevUser2@2020");
            sender.sendMail("This is Subject",
                    "This is Body",
                    "dev.transpose1002@gmail.com",
                    "user@yahoo.com");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }

//        String email = eTo.getText().toString().trim();
//        String subject = eSubject.getText().toString().trim();
//        String message = eMsg.getText().toString().trim();
//        SendMail sm = new SendMail(this, email, subject, message);
//        sm.execute();
    }
}