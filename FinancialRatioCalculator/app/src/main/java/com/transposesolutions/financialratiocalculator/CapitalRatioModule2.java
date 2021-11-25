package com.transposesolutions.financialratiocalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class CapitalRatioModule2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    // declare instance variables required for the navigation drawer
    private ActionBarDrawerToggle mToggle;
    TextView _message1, _message2, _message3, _message4, _message5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital_ratio_module2);
        // Load Navigation View, add toggle button to the drawer layout , setNavigationItemSelectedListener
        // and onOptionsItemSelected.
        DrawerLayout mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        //Declare UI elements
        _message1 = findViewById(R.id.result_1);
        _message2 = findViewById(R.id.result_2);
        _message3 = findViewById(R.id.result_3);
        _message4 = findViewById(R.id.result_4);
        _message5 = findViewById(R.id.result_5);
        // Retrieve the copied string from the other activity
        Bundle bun = getIntent().getExtras();
        String activity_message_1 = bun.getString(CapitalRatioModule1.RESULT);
        String activity_message_2 = bun.getString(CapitalRatioModule1.DETAIL);
        double activity_message_3 = Double.parseDouble(bun.getString(CapitalRatioModule1.USER_INPUT_1));
        double activity_message_4 = Double.parseDouble(bun.getString(CapitalRatioModule1.USER_INPUT_2));
        double activity_message_5 = Double.parseDouble(bun.getString(CapitalRatioModule1.USER_INPUT_3));
        // Paste the retrieved string to the current activity Text view
        _message1.setText(activity_message_1);
        _message2.setText(activity_message_2);
        _message3.setText(String.format(Locale.getDefault(),"%,.2f",activity_message_3));
        _message4.setText(String.format(Locale.getDefault(),"%,.2f",activity_message_4));
        _message5.setText(String.format(Locale.getDefault(),"%,.2f",activity_message_5));

    }

    public void activity_share(View view) {
        //On click event handler - When user taps share button
            // current activity string are copied to another set of string that are made available for the Share intent
            String activity_result;
            activity_result = _message1.getText().toString();
            String activity_detail;
            activity_detail = _message2.getText().toString();
            String activity_user_input_1;
            activity_user_input_1 = _message3.getText().toString();
            String activity_user_input_2;
            activity_user_input_2 = _message4.getText().toString();
            String activity_user_input_3;
            activity_user_input_3 = _message5.getText().toString();
            // above declared string are exported to share activity
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Your Return on Capital Employed Ratio: " + activity_result + ",\n"
                    + "What does it mean? " + activity_detail + "\n"+ "\n"
                    + "Your Input: " + "\n"
                    + "Earning Before Interest and Expense (EBIT): " + activity_user_input_1 + ",\n"
                    + "Total Assets: " + activity_user_input_2 + ",\n"
                    + "Total Liabilities: " + activity_user_input_3 + ".\n";
            String shareSub = "Your Return on Capital Employed is calculated by Transpose Solutions: Android App - Financial Ratio Calculator";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

    }
    // override method to responsible for responding correctly to the items specified in the menu resource file.
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // override method to listen for any click events on selecting a particular item from the drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.profitability) {
            Intent intent = new Intent(this, Profitability.class);
            startActivity(intent);
        } else if (id == R.id.liquidity) {
            Intent intent = new Intent(this, Liquidity.class);
            startActivity(intent);
        } else if (id == R.id.efficiency) {
            Intent intent = new Intent(this, Efficiency.class);
            startActivity(intent);
        } else if (id == R.id.leverage) {
            Intent intent = new Intent(this, Leverage.class);
            startActivity(intent);
        } else if (id == R.id.market) {
            Intent intent = new Intent(this, Market.class);
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
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Check out this financial ratio calculator: Finance utility app to analyze financial statement." +  "\n"+  "\n"
                    + "Google Play store:" +  "\n" +"https://play.google.com/store/apps/details?id=com.transposesolutions.financialratiocalculator&hl=en" + "\n";
            String shareSub = "Check out this financial ratio calculator from Transpose Solutions";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
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

        }
        return true;
    }

}