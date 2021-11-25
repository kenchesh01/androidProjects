package com.transposesolutions.loginassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class LocalLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActionBarDrawerToggle mToggle;
    TextView SignUp;
    EditText UserMail,UserPass;
    CheckBox checkBox;
    Button SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_login);
        // UI Elements
        SignUp = findViewById(R.id.sign_up);
        UserMail = findViewById(R.id.u_mail2);
        UserPass = findViewById(R.id.u_pass2);
        checkBox = findViewById(R.id.checkBox);
        SignIn = findViewById(R.id.sign_in2);

        /*navigation drawerlayout */
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName =UserMail .getText().toString();
                String userPassword = UserPass.getText().toString();
                if(userName.isEmpty()||userPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_SHORT).show();
                }else{
                    //
                    UserDataBase userDataBase = UserDataBase.getUserDataBase(getApplicationContext());
                    final UserDao userDao = userDataBase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity =userDao.login(userName,userPassword);
                            if(userEntity==null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid credential !", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{

                                Intent intent = new Intent(LocalLogin.this, Privilege.class);
                       startActivity(intent);
                            }
                        }
                    }).start();
                }


            }
        });




        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalLogin.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar,menu);
        return true;
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        // toolbar onOptionItemSelected
        if (item.getItemId() == R.id.mail) {
            upEmail();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void upEmail() {
        Intent intent = new Intent(this, Email.class);
        startActivity(intent);
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