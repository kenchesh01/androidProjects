package com.transposesolutions.bankingcalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PurchasesUpdatedListener {

    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;
    private AdView adView;    
    //Define UI Elements
    private TextView eDeposit, eSavings, eGoal;
    private BillingClient billingClient;
    private final List<String> skuList = new ArrayList<>();
    private final String productKey = "banking_1010";
    private SkuDetails mSkuDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiate Mobile Ads SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        skuList.add(productKey);
// Obtain the boolean value from ValidateStoreKey class using getPurchaseKey method.
// If the value is false - Ads will be enabled and if true (Paid User) Ads will be removed
//        Boolean validate = new ValidateStoreKey(this).getPurchaseKey(this,"myPref", productKey);
//        if(validate) {
//
//            ScrollView scrollView = findViewById(R.id.scroll);
//            DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams)scrollView.getLayoutParams();
//           // Set/Reset Margin for the Scroll View
//            layoutParams.topMargin = 10;
//            scrollView.setLayoutParams(layoutParams);
//            Toast.makeText(this, "you are a premium user", Toast.LENGTH_SHORT).show();
//
//        }else{
            Toast.makeText(this, "Free User", Toast.LENGTH_SHORT).show();
            // Load an ad into the AdMob banner view.
            FrameLayout adContainerView = findViewById(R.id.ad_view_container);
            // Step 1 - Create an AdView and set the ad unit ID on it.
            adView = new AdView(this);
            adView.setAdUnitId(getString(R.string.banner_ad_unit_id));
            adContainerView.addView(adView);
            loadBanner();
       // }

        // Load Navigation View, add toggle button to the drawer layout , setNavigationItemSelectedListener
        // and onOptionsItemSelected.
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // user check premium or free user
//        //buttonBuyProduct.setVisibility(View.INVISIBLE);
//        skuList.add(sku);
//        Boolean b = getBoolFromPref(this, "myPref", sku);
//        if (b) {
//            Toast.makeText(this, "you are a premium user", Toast.LENGTH_SHORT).show();
////            buttonBuyProduct.setVisibility(View.INVISIBLE);
//        } else {
//            setupBillingClient();
//        }
        
        // Bind the XML views to Java code Elements
        eDeposit = findViewById(R.id.label_deposit);
        eSavings = findViewById(R.id.label_savings);
        eGoal = findViewById(R.id.label_goal);
        // When user taps the Text - Deposit, user will be navigated to DepositModule1.java
        eDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DepositModule1.class));
                //If user is a paid customer update the status to the ValidateStoreKey Class
            }
        });
        // When user taps the Text - Savings, user will be navigated to SavingsModule1.java
        eSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SavingsModule1.class));
            }
        });
        // When user taps the Text - Savings Goal, user will be navigated to GoalModule1.java
        eGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GoalModule1.class));
            }
        });
    }

    private void setupBillingClient() {
        billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build();
        billingClient.startConnection(new BillingClientStateListener(){

            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is setup successfully
                    loadAllSKUs();
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });
    }


    private void loadAllSKUs() {
        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

        if (billingClient.isReady())
        {
            Toast.makeText(MainActivity.this, "billingclient ready", Toast.LENGTH_SHORT).show();
            SkuDetailsParams params = SkuDetailsParams.newBuilder()
                    .setSkusList(skuList)
                    .setType(BillingClient.SkuType.INAPP)
                    .build();

            billingClient.querySkuDetailsAsync(params, new SkuDetailsResponseListener() {
                @Override
                public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                    Toast.makeText(MainActivity.this, "inside query" + billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                            && !skuDetailsList.isEmpty())
                    {
                        for (Object skuDetailsObject : skuDetailsList) {
                            final SkuDetails skuDetails = (SkuDetails) skuDetailsObject;
                            Toast.makeText(MainActivity.this, "" + skuDetails.getSku(), Toast.LENGTH_SHORT).show();

                            if (skuDetails.getSku() == productKey)
                                mSkuDetails = skuDetails;
//                            buttonBuyProduct.setEnabled(true);

//                            buttonBuyProduct.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    BillingFlowParams billingFlowParams = BillingFlowParams
//                                            .newBuilder()
//                                            .setSkuDetails(skuDetails)
//                                            .build();
//                                    billingClient.launchBillingFlow(MainActivity.this, billingFlowParams);
//
//                                }
//                            });
                        }
                    }
                }
            });
        }
        else
            Toast.makeText(MainActivity.this, "billingclient not ready", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
        int responseCode = billingResult.getResponseCode();
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                && purchases != null) {
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        }
        else
        if (responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
            //Log.d(TAG, "User Canceled" + responseCode);
        }
        else if (responseCode == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            ///mSharedPreferences.edit().putBoolean(getResources().getString(R.string.pref_remove_ads_key), true).commit();
            ///setAdFree(true);
            setBoolInPref(this,"myPref",productKey, true );
        }
        //Log.d(TAG, "Other code" + responseCode);
        // Handle any other error codes.


    }

    private void handlePurchase(Purchase purchase) {
        if (purchase.getSkus().equals(productKey)) {
            ///mSharedPreferences.edit().putBoolean(getResources().getString(R.string.pref_remove_ads_key), true).commit();
            ///setAdFree(true);
            setBoolInPref(this,"myPref",productKey, true );
            Toast.makeText(this, "Purchase done. you are now a premium member.", Toast.LENGTH_SHORT).show();
        }
    }


    private Boolean getBoolFromPref(Context context, String prefName, String constantName) {
        SharedPreferences pref = context.getSharedPreferences(prefName, 0); // 0 - for private mode

        return pref.getBoolean(constantName, false);

    }

    private void setBoolInPref(Context context,String prefName, String constantName, Boolean val) {
        SharedPreferences pref = context.getSharedPreferences(prefName, 0); // 0 - for private mode

        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(constantName, val);
        editor.commit();
    }





            /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    private void loadBanner() {
        // Create an ad request. Check your logcat output for the hashed device ID
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = getAdSize();
        // Step 4 - Set the adaptive ad size on the ad view.
        adView.setAdSize(adSize);
        // Step 5 - Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    private AdSize getAdSize() {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

    // onClick Handler called when the user taps the button - savings goal Calculator
    public void send_savings_goal_calculator(View view) {
        Intent intent = new Intent(this, GoalModule1.class);
        startActivity(intent);
    }
    // onClick Handler called when the user taps the button - Savings Calculator
    public void send_savings_calculator(View view) {
        Intent intent = new Intent(this, SavingsModule1.class);
        startActivity(intent);
    }

    // onClick Handler called when the user taps the button - CD Calculator
    public void send_deposit_Calculator(View view) {
        Intent intent = new Intent(this, DepositModule1.class);
        startActivity(intent);
    }

// override method to responsible for responding correctly to the items specified in the menu resource file.
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.tool_menu,menu);
    return true;
}
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
// toolbar onOptionItemSelected
        if (item.getItemId() == R.id.purchase) {
          //  appPurchase();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // Buy button
//toolbar
    // override method to listen for any click events on selecting a particular item from the drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_home) {
            // Handle the camera action
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.CD) {
            Intent intent = new Intent(this, DepositModule1.class);
            startActivity(intent);
        } else if (id == R.id.savings) {
//            new ValidateStoreKey(this).setPurchaseKey(this,"myPref",productKey, false );
            Intent intent = new Intent(this, SavingsModule1.class);
            startActivity(intent);
        } else if (id == R.id.investedCalculator) {
            Intent intent = new Intent(this, ReturnModule1.class);
            startActivity(intent);
        }else if (id == R.id.savings_goal) {
            Intent intent = new Intent(this, GoalModule1.class);
            startActivity(intent);

        } else if (id == R.id.nav_rate) {
            try {
                Uri uri1 = Uri.parse("market://details?id=" + getPackageName());
                Intent gotoMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket1);
            } catch (ActivityNotFoundException e){
                Uri uri1 = Uri.parse("http://play.google.com/store/apps/details/id=" + getPackageName());
                Intent gotoMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket1);
            }
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Check out this great Banking Calculator app. This app helps you to simulate your future savings based on the compound interest formula " +  "\n"+  "\n"
                    + "Google Play store:" +  "\n" +"https://play.google.com/store/apps/details?id=com.transposesolutions.bankingcalculator&hl=en" + "\n";
            String shareSub = "Check out this great Banking Calculator app from Transpose Solutions";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        } else if (id == R.id.nav_apps) {
            try {
                Uri uri1 = Uri.parse("https://play.google.com/store/apps/dev?id=8903808498078108637&hl=en");
                Intent gotoMarket2 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket2);
            } catch (ActivityNotFoundException e) {
                Uri uri1 = Uri.parse("https://play.google.com/store/apps/dev?id=8903808498078108637&hl=en");
                Intent gotoMarket2 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(gotoMarket2);
            }
        }else if(id ==R.id.store_purchase){
            //If user is a paid customer update the status to the ValidateStoreKey Class
//            new ValidateStoreKey(this).setPurchaseKey(this,"myPref",productKey, true );
            Toast.makeText(MainActivity.this,"congrats your Removed the ads",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    // called when the user tapped device back button override it with onBackPressed() to ask if they want exit the app or stay?
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void send_return_calculator(View view) {
        startActivity(new Intent(MainActivity.this,ReturnModule1.class));
    }
}