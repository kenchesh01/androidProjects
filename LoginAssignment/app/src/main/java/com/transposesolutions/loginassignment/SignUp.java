package com.transposesolutions.loginassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText name,mail,password;
    TextView tSigIn;
    Button register;
    UserDataBase userDataBase;
    boolean isNameValid, isEmailValid,  isPasswordValid;
    FirebaseAuth firebaseAuth;
FirebaseFirestore fStore;
String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userDataBase = UserDataBase.getUserDataBase(getApplicationContext());

        firebaseAuth = firebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        name = findViewById(R.id.uName);
        mail = findViewById(R.id.ugmail);
        password = findViewById(R.id.upass);
        register = findViewById(R.id.register);
        tSigIn = findViewById(R.id.tLoginCloud);
        /* sigIn onclick */
         tSigIn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(SignUp.this,CloudLogin.class));
             }
         });




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = mail.getText().toString().trim();
                String sPassword = password.getText().toString().trim();
                String sName = name.getText().toString().trim();
                // Check for a valid name.
                if (name.getText().toString().isEmpty()) {
                    name.setError(getResources().getString(R.string.name_error));
                    isNameValid = false;
                } else if (name.getText().length() < 2) {
                    name.setError(getResources().getString(R.string.error_invalid_name));
                    isPasswordValid = false;
                } else  {
                    isNameValid = true;
                }

                // Check for a valid email address.
                if (mail.getText().toString().isEmpty()) {
                    mail.setError(getResources().getString(R.string.email_error));
                    isEmailValid = false;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches()) { mail.setError(getResources().getString(R.string.error_invalid_email));
                    isEmailValid = false;
                } else  {
                    isEmailValid = true;
                }


                // Check for a valid password.
                if (password.getText().toString().isEmpty()) {
                    password.setError(getResources().getString(R.string.password_error));
                    isPasswordValid = false;
                } else if (password.getText().length() < 6) {
                    password.setError(getResources().getString(R.string.error_invalid_password));
                    isPasswordValid = false;
                } else  {
                    isPasswordValid = true;
                }

                // fire base Authorization response
                firebaseAuth.createUserWithEmailAndPassword(sEmail,sPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignUp.this," Sucessfully registered",Toast.LENGTH_LONG).show();
                                    userId= firebaseAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fStore.collection("users").document(userId);
                                    Map<String,Object> user = new HashMap<>();
                                   user.put("fName",sName);
                                    user.put("email",sEmail);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            UserEntity userEntity = new UserEntity();
                                            userEntity.setName(sName);
                                            userEntity.setEmail(sEmail);
                                            userEntity.setPassWord(sPassword);
                                            final UserDao userDao = userDataBase.userDao();
                                            Thread thread = new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    userDao.addUser(userEntity);
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(getApplicationContext(), "User Register Successfully!", Toast.LENGTH_LONG).show();
                                                            Log.d("TAG"," profile name"+sName);
                                                            Log.d("TAG","user profile email"+sEmail);
                                                            Log.d("TAG"," user profile password "+sPassword);
                                                        }
                                                    });
                                                }
                                            });
                                            thread.start();

                                            Log.d("TAG","success user profile "+userId);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("TAG","failed "+e.toString());
                                        }
                                    });
                                    startActivity(new Intent(SignUp.this,MainActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(SignUp.this,"user registered failed",Toast.LENGTH_LONG).show();

                                }
                            }
                        });
//                List<UserEntity> test  = userDataBase.userDao().getUsersList();
//                if(test.size()<1){
//                    addUserInfo();
//                } else {
//                    userDataBase.userDao().deleteTable();
//                    addUserInfo();
//                }
            }
        });



    }




    private void addUserInfo() {
        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            name.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else if (name.getText().length() < 2) {
            name.setError(getResources().getString(R.string.error_invalid_name));
            isPasswordValid = false;
        } else  {
            isNameValid = true;
        }

        // Check for a valid email address.
        if (mail.getText().toString().isEmpty()) {
            mail.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mail.getText().toString()).matches()) { mail.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }


        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }
        if (isNameValid && isEmailValid && isPasswordValid) {
            String userName = name.getText().toString();
            String userEmail = mail.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            UserEntity userEntity = new UserEntity();
            userEntity.setName(userName);
            userEntity.setEmail(userEmail);
            userEntity.setPassWord(userPassword);

            final UserDao userDao = userDataBase.userDao();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    userDao.addUser(userEntity);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "User Register Successfully!", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            });
            thread.start();

     }
    }
}