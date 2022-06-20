package com.example.loginactivity.fireBase;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.loginactivity.ContactActivity;
import com.example.loginactivity.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyService extends FirebaseMessagingService {
    public MyService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

            super.onMessageReceived(remoteMessage);
            if (remoteMessage.getNotification() != null) {

                createNotificationChannel();

                String n=remoteMessage.getData().get("From");
                Intent i=new Intent(this, ContactActivity.class);
                i.putExtra( "idContact",remoteMessage.getData().get("From"));
                i.putExtra("nameContact",remoteMessage.getData().get("From"));
                i.putExtra("serverContact","10.0.2.2:1234");
                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,i,  PendingIntent.FLAG_UPDATE_CURRENT);

//            startActivity(intent);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"1")
                        .setSmallIcon(R.drawable.profile_pic)
                        .setContentTitle(remoteMessage.getNotification().getTitle())
                        .setContentText(remoteMessage.getNotification().getBody())
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setPriority(Notification.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
                notificationManagerCompat.notify(1, builder.build());
            }
        }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1","My channel",importance);
            channel.setDescription("add contacts channel");

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

}