package com.example.notification;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class NotificationHelper {

    private static final String CHANNEL_ID = "default_channel";
    private static final String CHANNEL_NAME = "Kanał powiadomień";


    public static void sendNotification(int NOTIFICATION_ID, AppCompatActivity activity, String title, String message, int styleType, int largeIconResID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, new String [] {android.Manifest.permission.POST_NOTIFICATIONS}, 100);
                return;
            }
        };
        NotificationManager notificationManager = (NotificationManager)
                activity.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(activity, CHANNEL_ID)
                        .setSmallIcon(R.drawable.kotek)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);

        if (largeIconResID != 0){
            Bitmap largeIcon = BitmapFactory.decodeResource(activity.getResources(), largeIconResID);
            builder.setLargeIcon(largeIcon);
        }

        switch (styleType){

        }
    }
}
