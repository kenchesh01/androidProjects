package com.transposesolutions.notification;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RegisterAlarm();
        createNotification();
    }

//    private void startTime() {
//        // Time is in millisecond so 50sec = 50000 I have used
//        // countdown Interval is 1sec = 1000 I have used
//        new CountDownTimer(50000, 1000) {
//            public void onTick(long millisUntilFinished) {
//                // Used for formatting digit to be in 2 digits only
//                long hour = (millisUntilFinished / 3600000) % 24;
//                long min = (millisUntilFinished / 60000) % 60;
//                long sec = (millisUntilFinished / 1000) % 60;
////                textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
//            }
//            // When the task is over it will print 00:00:00 there
//            public void onFinish() {
////                textView.setText("00:00:00");
//            }
//        }.start();
//    }




    private void createNotification() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence sequence = "testing";
                String testingContent =" Kenchesh  notification";
                int important = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel notificationChannel = new NotificationChannel("MyNotification",sequence, important);
                notificationChannel.setDescription(testingContent);

                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(notificationChannel);
            }
    }

    private void RegisterAlarm() {

        Calendar calendar = Calendar.getInstance();
//        calendar .set(Calendar. DAY_OF_MONTH ,1) ;
        calendar.set(Calendar.HOUR_OF_DAY,17);
        calendar.set(Calendar.MINUTE,17);
        calendar.set(Calendar.SECOND,0);

        Intent intent = new Intent(MainActivity.this,NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager)this.getSystemService(ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

    }


    public void test(View view) {
        Intent intent = new Intent(this,TestCase.class);
        startActivity(intent);
    }
}