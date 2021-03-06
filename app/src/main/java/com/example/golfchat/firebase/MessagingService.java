package com.example.golfchat.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;

public class MessagingService extends FirebaseMessagingService {

    // FCM : Firebase Cloud Messaging
    @Override
    public void onNewToken(@NonNull String token){
        super.onNewToken(token);
//        Log.d("FCM", "Token: " + token);
    }

    // When sending data messages, you need to handle the messages in the onMessageReceived() callback in the client app.
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);
//        Log.d("FCM", "Message: " + Objects.requireNonNull(remoteMessage.getNotification()).getBody());
    }


}
