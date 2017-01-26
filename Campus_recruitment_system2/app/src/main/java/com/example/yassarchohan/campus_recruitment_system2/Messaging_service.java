package com.example.yassarchohan.campus_recruitment_system2;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Yassar chohan on 1/26/2017.
 */
public class Messaging_service extends FirebaseMessagingService {

    public static final String TAG = "messagingService";
    private NotificationCompat.Builder notification;
    private Getter_methods gm;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG , "Message is :"+remoteMessage);

        if(remoteMessage.getData() !=null){
            Log.d(TAG , "your message is :" + remoteMessage.getData());
        }




        if(remoteMessage.getNotification() !=null){
            Log.d(TAG , "your message body" + remoteMessage.getNotification().getBody());
            //   send(remoteMessage.getNotification().getBody());
        }

    }

}
