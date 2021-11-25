package com.transposesolutions.currencyconverter;

import android.app.Application;

public class MyApplication extends Application {
    public int getCustomCountriesZone() {
        return customCountriesZone;
    }

    public void setCustomCountriesZone(int customCountriesZone) {
        this.customCountriesZone = customCountriesZone;
    }

    int customCountriesZone =1;


}
