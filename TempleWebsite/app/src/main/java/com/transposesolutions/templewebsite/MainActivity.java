package com.transposesolutions.templewebsite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // If the Android version is lower than Jellybean, use this call to hide
        // the status bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // this line hide the action bar
        setContentView(R.layout.activity_main);

        CustomWebViewClient client = new CustomWebViewClient(this);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(client);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://htnew.org");
        WebSettings settings = webView.getSettings();
        settings.setAllowFileAccess(true);

// Initialize and assign values
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        // set home Activity
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                    // Handle the camera action
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);}
                if (id == R.id.navigation_second) {
                    // Handle the camera action
                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(intent);}

                return false;
            }
        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

class CustomWebViewClient extends WebViewClient {
    private Activity activity;

    public CustomWebViewClient(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        return false;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
        return false;
    }
}