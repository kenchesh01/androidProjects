package com.transposesolutions.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button module1,module2,module3,module4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        module1= findViewById(R.id.module_one);
        module2 = findViewById(R.id.module_two);
        module3 =findViewById(R.id.module_three);
        module4 = findViewById(R.id.module_four);
        // module1 onclick listener
        module1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CurrencyMajorsCurrency.class);
                startActivity(intent);
            }
        });
        // module2 onclick listener
        module2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CurrencyModule2.class);
                startActivity(intent);
            }
        });
        // module3 onclick listener
        module3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CurrencyModule5.class);
                startActivity(intent);
            }
        });
        module4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Currencytesting.class);
                startActivity(intent);
            }
        });

    }
}