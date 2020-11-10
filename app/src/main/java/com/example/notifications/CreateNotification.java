package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * as icon in status bar, visible until dismissed
 * Heads up Notifaction, only if phone is unlocked.
 * Conditions, Uses fullScreenIntent (only if users activity is in fullscreen mode and uses vibration or ringtone
 * or on a locked screen
 *
 * SHould include a task, like do it now?
 *
 * Must be assigned to a channel, in our case two for each type of notification
 * user can alter importance
 *
 *https://developer.android.com/training/notify-user/build-notification
 *
 *https://github.com/android/user-interface-samples/blob/main/Notifications/Application/src/main/java/com/example/android/wearable/wear/wearnotifications/MainActivity.java
 * https://developer.android.com/reference/android/app/AlarmManager.html
 */
public class CreateNotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //main
    }

    public void createNotification(View view) {
        // prepare intent which is triggered if the notification is selected

        Intent intent = new Intent(this, NotificationReceiverActivity.class);

        // use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int)
                System.currentTimeMillis(), intent, 0);

    }

    // build notification the addAction re-use the same intent to keep the example short
    Notification n = new Notification.Builder()
            .setContentTitle("Don't Forget your goal to " + "Read your scriptures")
            .setContentText("Scriptures")
            .setSmallIcon(R.drawable.k)
            .setContentIntent(PendingIntent.readPendingIntentOrNullFromParcel()) //pIntent
            .setAutoCancel(true).build();
    //.addAction(R.drawable.icon, "Call", pIntent)

    NotificationManager notificationManager = (NotificationManager)
            getSystemService(NOTIFICATION_SERVICE);

    //hide the notification after it's selected.
   n.flags |= Notification.FLAG_AUTO_CANCEL;

    notificationManager.notify(0,n);