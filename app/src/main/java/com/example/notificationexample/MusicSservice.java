package com.example.notificationexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.TimerTask;

public class MusicSservice extends Service {
    MediaPlayer mediaPlayer;
    NotificationManager manager;
    private boolean timer;

    public MusicSservice() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this, R.raw.thornsinst);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // normal intent
        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1,
                PendingIntent.FLAG_MUTABLE);
        manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationChannel channel=new NotificationChannel("pain","music", NotificationManager.IMPORTANCE_LOW);
        manager.createNotificationChannel(channel);
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, "pain").setChannelId("pain")
                .setContentText("hi").setContentTitle("Musik iz playing").setLights(Color.RED,100,100).setSmallIcon(R.drawable.ic_launcher_background).setColor(Color.RED).setContentIntent(pendingIntent);
        Notification notification=nBuilder.build();
        manager.notify(10000, notification);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(10000);
        mediaPlayer.stop();
    }
    
    class MyTimerTask extends TimerTask {


        @Override
        public void run() {
            timer=true;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}