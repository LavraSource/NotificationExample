
package com.example.notificationexample;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class MyIntentService extends IntentService {

    private Timer mTimer;
    private MusicSservice.MyTimerTask mMyTimerTask;
    public boolean timer=false;

    private static final String ACTION_FOO = "com.example.notificationexample.action.FOO";
    private static final String ACTION_BAZ = "com.example.notificationexample.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.example.notificationexample.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.notificationexample.extra.PARAM2";

    public MyIntentService() {
        super("MyIntentService");
    }


    @Override
    public void onCreate() {
        super.onCreate();


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onHandleIntent(Intent intent) {
        String hours_text=intent.getStringExtra("hours");
        String minutes_text=intent.getStringExtra("minutes");
        String seconds_text=intent.getStringExtra("seconds");
        int hours_number=-1;
        int minutes_number=-1;
        int seconds_number=-1;
        try {
            hours_number=Integer.parseInt(hours_text);
            minutes_number=Integer.parseInt(minutes_text);
            seconds_number=Integer.parseInt(seconds_text);
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "введите числа", Toast.LENGTH_SHORT).show();
        }

        try {
            TimeUnit.SECONDS.sleep(seconds_number + minutes_number * 60 + hours_number * 3600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (hours_number!=-1&&minutes_number!=-1&&seconds_number!=-1) {
            Intent intent1 = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("MusicChannel", "Music", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, "MusicChannel")
                    .setChannelId("MusicChannel")
                    .setContentTitle("beep")
                    .setContentText("boop")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setColor(Color.BLUE)
                    .setContentIntent(pendingIntent);
            Notification notification = nBuilder.build();
            manager.notify(475648, notification);
        }


    }




    private void handleActionFoo(String param1, String param2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void handleActionBaz(String param1, String param2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}