package com.transposesolutions.flyingbirds;

import android.app.Application;

public class GameTracker extends Application {
    // main activity load time , this bool condition will be check
    boolean  gameOption = true;
    public boolean getGameOption() {
        return gameOption;
    }

    public void setGameOption(boolean gameOption) {
        this.gameOption = gameOption;
    }
    // this bool condition is used on game view classes , when game is over or not it will be check
    boolean   gameStatus =false;

    public boolean getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }





}
