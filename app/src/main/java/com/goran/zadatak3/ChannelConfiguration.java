package com.goran.zadatak3;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class ChannelConfiguration extends Application {
    public static final String CHANNEL_1="channel_1";
    public static final String CHANNEL_2="channel_2";
    @Override
    public void onCreate() {
        super.onCreate();
        createChannels();
    }

    private void createChannels() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            NotificationChannel channel1=new NotificationChannel(CHANNEL_1,"channel_1", NotificationManager.IMPORTANCE_HIGH);
            channel1.setLightColor(android.R.color.holo_red_light);
            channel1.enableLights(true);
            channel1.enableVibration(true);
            channel1.setDescription("Notification channel with high importance");

            NotificationChannel channel2=new NotificationChannel(CHANNEL_2,"channel_2",NotificationManager.IMPORTANCE_DEFAULT);
            channel2.setLightColor(android.R.color.holo_blue_light);
            channel2.enableVibration(false);
            channel2.enableLights(true);
            channel2.setDescription("Notification channel with default importance");

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
            notificationManager.createNotificationChannel(channel2);
        }

    }
}
