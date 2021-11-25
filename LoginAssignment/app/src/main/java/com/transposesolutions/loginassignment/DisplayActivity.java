package com.transposesolutions.loginassignment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;


public class DisplayActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
 private AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
private  String userIDS;

    TextView textView;
UserDataBase userDataBase;
//
    TextView tvUserName1;
    TextView tvUserEmail1;
    ImageView userImageView1,userImageView;
    Button btnSignOut1;

    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        // mail info
 textView = findViewById(R.id.user_input);

//        List<UserEntity> test  = userDataBase.userDao().getUsersList();
//        for(UserEntity userEntity :test){
//          String  pass = userEntity.getName();
//            textView.setText(pass);
//        }
        userImageView = findViewById(R.id.userImage);
        userImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProfilePicture();
            }
        });

fAuth= FirebaseAuth.getInstance();
fStore = FirebaseFirestore.getInstance();
userIDS = fAuth.getCurrentUser().getUid();
DocumentReference documentReference = fStore.collection("users").document(userIDS);
documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
    @Override
    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
        String name = documentSnapshot.getString("fName");
//        textView.setText(name);
        Log.d("Log",name);
    }
});



        // mail info


        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = preferences.getString("username","");

//        textView.setText(userName);


        /*navigation drawerlayout */
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



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
            Intent intent =new Intent(this,CloudLogin.class);
            startActivity(intent);
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile,menu);
        return true;
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        if(item.getItemId() == R.id.actionProfile){
            updateProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateProfile() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View profileView = getLayoutInflater().inflate(R.layout.popup,null);
        // mail info
        tvUserName1 =profileView.findViewById(R.id.userName1);
        tvUserEmail1 = profileView.findViewById(R.id.userEmail1);
        userImageView1 = profileView.findViewById(R.id.userImage);
        btnSignOut1 = profileView.findViewById(R.id.btnLogout1);


        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = preferences.getString("username","");
        String userEmail = preferences.getString("useremail", "");
        String userImageUrl = preferences.getString("userPhoto","");
        tvUserName1.setText(userName);
        tvUserEmail1.setText(userEmail);
        byte[] byteArray1;
        byteArray1 = Base64.decode(userImageUrl, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray1, 0,
                byteArray1.length);
        userImageView1.setImageBitmap(bmp);
//        Glide.with(this).load(userImageUrl).into(userImageView1);

        dialogBuilder.setView(profileView);
        dialog =dialogBuilder.create();
        dialog.show();
//        dialog.getWindow().setLayout(700, 1200);
        btnSignOut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                AppTracker validate = (AppTracker)getApplicationContext();
                validate.setButtonCheck(true);
                startActivity(new Intent(DisplayActivity.this,MainActivity.class));
            }
        });
        userImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProfilePicture();
            }
        });

    }


        public void convertStringToBitmap(String string) {
           /* w  w  w.ja va 2 s  .  c om*/

    }

    private void chooseProfilePicture() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_profile_picture, null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageView imageViewADPPCamera = dialogView.findViewById(R.id.imageViewADPPCamera);
        ImageView imageViewADPPGallery = dialogView.findViewById(R.id.imageViewADPPGallery);

        final AlertDialog alertDialogProfilePicture = builder.create();
        alertDialogProfilePicture.show();

        imageViewADPPCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAndRequestPermissions()) {
                    takePictureFromCamera();
                    alertDialogProfilePicture.dismiss();
                }
            }
        });

        imageViewADPPGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureFromGallery();
                alertDialogProfilePicture.dismiss();
            }
        });
    }

    private void takePictureFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    private void takePictureFromCamera(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePicture, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImageUri = data.getData();
                    userImageView1.setImageURI(selectedImageUri);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
                    userImageView1.setImageBitmap(bitmapImage);
                }
                break;
        }
    }

    private boolean checkAndRequestPermissions(){
        if(Build.VERSION.SDK_INT >= 23){
            int cameraPermission = ActivityCompat.checkSelfPermission(DisplayActivity.this, Manifest.permission.CAMERA);
            if(cameraPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(DisplayActivity.this, new String[]{Manifest.permission.CAMERA}, 20);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 20 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            takePictureFromCamera();
        }
        else
            Toast.makeText(DisplayActivity.this, "Permission not Granted", Toast.LENGTH_SHORT).show();
    }


}