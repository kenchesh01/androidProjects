package com.transposesolutions.loginassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
EditText resetEmailId;
Button resetButton;
ProgressBar pBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        resetEmailId = findViewById(R.id.rEmail);
        resetButton = findViewById(R.id.rSetButton);
        pBar = findViewById(R.id.rPBar);

        firebaseAuth = firebaseAuth.getInstance();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rEmail = resetEmailId.getText().toString().trim();

                if (TextUtils.isEmpty(rEmail)) {
                    resetEmailId.setError("Please Enter Register Mail Id");
                    return;
                }  else if (!Patterns.EMAIL_ADDRESS.matcher(rEmail).matches()) {
                    resetEmailId.setError("Enter valid mail ID");
                    return;
                }
                pBar.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(rEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful())  {
                          pBar.setVisibility(View.INVISIBLE);
                          Toast.makeText(ResetPassword.this,"Check Your email to reset your password",Toast.LENGTH_LONG).show();
                      }else {
                          Toast.makeText(ResetPassword.this,"Try Again! Something Wrong happened !",Toast.LENGTH_LONG).show();

                      }
                    }
                });

            }
        });


    }
}