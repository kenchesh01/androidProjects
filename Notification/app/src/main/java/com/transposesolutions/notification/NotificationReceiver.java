package com.transposesolutions.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"MyNotification");

        Intent NotificationIntent = new Intent(context,MainActivity.class);
        NotificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,NotificationIntent ,PendingIntent.FLAG_UPDATE_CURRENT );
         Uri AlarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        builder.setSmallIcon(R.drawable.ic_android);
        builder.setContentTitle("testing");
        builder.setContentText("testing notification");
        builder.setAutoCancel(true);
        builder.setWhen(when);
        builder.setContentIntent(pendingIntent);
        builder.setSound(AlarmSound);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(12, builder.build());

    }
}
