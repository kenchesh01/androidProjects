package com.transposesolutions.loginassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class BasicLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  EditText email,password;
    private ActionBarDrawerToggle mToggle;
  Button signin;
  TextView textView;
  int count=3;
    public static final String SEND_MESSAGE1 = "com.transposesolutions.loginassignment.First_Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_login);
        email = findViewById(R.id.u_mail);
        password = findViewById(R.id.u_pass);
        signin = findViewById(R.id.sign_in);
        textView = findViewById(R.id.attempt);
        Toast.makeText(getApplicationContext(), "Please enter your name and ensure password is more than 6  charachers long!", Toast.LENGTH_SHORT).show();
        TextWatcher mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                checkForEmptyValue();
            }};

            checkForEmptyValue();
            // set listeners
            email.addTextChangedListener(mTextWatcher);
            password.addTextChangedListener(mTextWatcher);

           signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("user1@test.com") && password.getText().toString().equals("1234567")){
                    Toast.makeText(getApplicationContext(), "Successful",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(BasicLogin.this, DisplayActivity.class);
                    Bundle bundle=new Bundle();
                    String name=email.getText().toString();
                    bundle.putString(SEND_MESSAGE1,name);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    textView.setVisibility(View.VISIBLE);
                    textView.setBackgroundColor(Color.RED);
                    count--;
                    String value = Integer.toString(count);
                    textView.setText("attempts left:" +value);

                    if (count == 0) {
                        signin.setEnabled(false);
                    }
                }
            }
        });
        /*navigation drawerlayout */
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    // To check for empty values on create
    private void checkForEmptyValue() {
        Button button = signin;
        String s1 = email.getText().toString();
        String s2 = password.getText().toString();
        if (s1.equals("") || s2.equals(""))
        { button.setEnabled(false);
        } else {
            button.setEnabled(true); }
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.basic_login) {
            Intent intent = new Intent(this, BasicLogin.class);
            startActivity(intent);
        } else if (id == R.id.local_login) {
            Intent intent = new Intent(this, LocalLogin.class);
            startActivity(intent);
        } else if (id ==R.id.cloud_login){
            Intent intent =new Intent (this,CloudLogin.class);
            startActivity(intent);
        }
        return true;
    }
}