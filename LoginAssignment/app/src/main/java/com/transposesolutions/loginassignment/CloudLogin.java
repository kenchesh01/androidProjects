package com.transposesolutions.loginassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.List;

public class CloudLogin extends AppCompatActivity {
    EditText cUserMail, cUserPass;
    TextView cUserSigUp, cResetPassword;
    Button cUserLoginButton;
    ImageView btnGoogle;
    FirebaseAuth firebaseAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    String pass,Mail;
    UserDataBase userDataBase;

    // sign in with google account
    public static final String TAG = "GoogleSignIn";
    public static final int RC_SIGN_IN = 321;
    private GoogleSignInClient mGoogleSignInClient;

    //
    public static final String SEND_MESSAGE1 = "com.transposesolutions.loginassignment.MAIL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_login);
        firebaseAuth = firebaseAuth.getInstance();
        mUser = firebaseAuth.getCurrentUser();
        requestGoogleSignIn();
        // UI Components
        cUserMail = findViewById(R.id.c_eMail);
        cUserPass = findViewById(R.id.c_ePass);
        cUserLoginButton = findViewById(R.id.c_sign_in);
        cUserSigUp = findViewById(R.id.cSign_up);
        cResetPassword = findViewById(R.id.c_resetPassword);
        btnGoogle = findViewById(R.id.googleImage);




        //SigUp onclick response
        cUserSigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CloudLogin.this, SignUp.class);
                startActivity(intent);
            }
        });
        //Reset Password  onclick response
        cResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CloudLogin.this, ResetPassword.class);
                startActivity(intent);
            }
        });


        cUserLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = cUserMail.getText().toString().trim();
                String password = cUserPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    cUserMail.setError("enter your email");
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    cUserPass.setError("enter your password");
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    cUserMail.setError("Enter valid mail ID");
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(CloudLogin.this, "Successfully Log in", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(CloudLogin.this, DisplayActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString(SEND_MESSAGE1, email);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    AppTracker validate = (AppTracker)getApplicationContext();
                                    validate.setButtonCheck(false);
                                    finish();
                                } else {
                                    Toast.makeText(CloudLogin.this, "Sig in filed", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
            }
        });
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(CloudLogin.this);
                progressDialog.setMessage("please wait...");
                progressDialog.show();
                signIn();
                AppTracker validate = (AppTracker)getApplicationContext();
                validate.setButtonCheck(false);
            }
        });



    }


    private void requestGoogleSignIn() {

        // Configure sign-in to request the user’s basic profile like name and email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void firebaseAuthWithGoogle(String idToken) {

        //getting user credentials with the help of AuthCredential method and also passing user Token Id.
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        //trying to sign in user using signInWithCredential and passing above credentials of user.
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "signInWithCredential:success");

                            // Sign in success, navigate user to Profile Activity
                            Intent intent = new Intent(CloudLogin.this, DisplayActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(CloudLogin.this, "User authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
















//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
////                firebaseAuthWithGoogle(account.getIdToken());
//            } catch (ApiException e) {
//                progressDialog.dismiss();
//                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
//            }
//        }
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating user with firebase using received token id
                firebaseAuthWithGoogle(account.getIdToken());

                //assigning user information to variables
                String userName = account.getDisplayName();
                String userEmail = account.getEmail();
                String userPhoto = account.getPhotoUrl().toString();
                userPhoto = userPhoto + "?type=large";
                //create sharedPreference to store user data when user signs in successfully
                SharedPreferences.Editor editor = getApplicationContext()
                        .getSharedPreferences("MyPrefs", MODE_PRIVATE)
                        .edit();
                editor.putString("username", userName);
                editor.putString("useremail", userEmail);
                editor.putString("userPhoto", userPhoto);
                editor.apply();

                Log.i(TAG, "onActivityResult: Success");

            } catch (ApiException e) {
                Log.e(TAG, "onActivityResult: " + e.getMessage());
            }
        }


//        private void firebaseAuthWithGoogle (String idToken){
//
//            //getting user credentials with the help of AuthCredential method and also passing user Token Id.
//            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//            //trying to sign in user using signInWithCredential and passing above credentials of user.
//            firebaseAuth.signInWithCredential(credential)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//
//                                Log.d(TAG, "signInWithCredential:success");
//                                progressDialog.dismiss();
//                                FirebaseUser user = firebaseAuth.getCurrentUser();
//                                // Sign in success, navigate user to Profile Activity
//                                Intent intent = new Intent(CloudLogin.this, DisplayActivity.class);
//                                startActivity(intent);
//
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                progressDialog.dismiss();
//                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInWithCredential:failure", task.getException());
//                                Toast.makeText(CloudLogin.this, "User authentication failed", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//        }
    }
}


