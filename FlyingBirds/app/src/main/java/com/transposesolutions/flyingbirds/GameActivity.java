package com.transposesolutions.flyingbirds;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {
    private GameView gameView;
    private GameView2 gameView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        gameView2 = new GameView2(this, point.x, point.y);
        gameView = new GameView(this, point.x, point.y);
        // check the game option condition
        GameTracker gameTracker = (GameTracker) getApplicationContext();
        boolean mgameOption = gameTracker.getGameOption();
        if (mgameOption) {
            setContentView(gameView2);
        } else {
            setContentView(gameView);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        // check the game option condition
        GameTracker gameTracker = (GameTracker) getApplicationContext();
        boolean mGameOption = gameTracker.getGameOption();
        if (mGameOption) {
            gameView2.pause();
        } else {
            gameView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
      // check the game option condition
        GameTracker gameTracker = (GameTracker) getApplicationContext();
        boolean mGameOption = gameTracker.getGameOption();
        if (mGameOption) {
            gameView2.resume();
        } else {
            gameView.resume();
        }
    }


}