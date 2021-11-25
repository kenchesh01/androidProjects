package com.transposesolutions.loginassignment;

import android.app.Application;

public class AppTracker extends Application {
    public boolean getButtonCheck() {
        return buttonCheck;
    }

    public void setButtonCheck(boolean buttonCheck) {
        this.buttonCheck = buttonCheck;
    }

    boolean buttonCheck = true;
}
