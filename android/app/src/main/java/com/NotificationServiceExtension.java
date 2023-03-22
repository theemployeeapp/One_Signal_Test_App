package com.awesomeproject; // replace awesomeproject with your app’s name

import android.content.Context;
import android.util.Log;
import org.json.JSONObject;

import com.onesignal.OSNotification;
import com.onesignal.OSMutableNotification;
import com.onesignal.OSNotificationReceivedEvent;
import com.onesignal.OneSignal.OSRemoteNotificationReceivedHandler;

import com.awesomeproject.NotificationServiceExtensionModule;


@SuppressWarnings("unused")
public class NotificationServiceExtension implements OSRemoteNotificationReceivedHandler {

    @Override
    public void remoteNotificationReceived(Context context, OSNotificationReceivedEvent notificationReceivedEvent) {
        OSNotification notification = notificationReceivedEvent.getNotification();

        // Example of modifying the notification's accent color
        OSMutableNotification mutableNotification = notification.mutableCopy();

        JSONObject data = notification.getAdditionalData();
        Log.i("OneSignalExample", "Received Notification Data: " + data);
        Log.i("OneSignalExample", "NotificationServiceExtensionModule.instance: " + NotificationServiceExtensionModule.instance);


        if (NotificationServiceExtensionModule.instance != null ) {
            NotificationServiceExtensionModule.instance.emitNotificationEvent();
        }

        // If complete isn't call within a time period of 25 seconds, OneSignal internal logic will show the original notification
        // To omit displaying a notification, pass `null` to complete()
        notificationReceivedEvent.complete(mutableNotification);
    }
}
