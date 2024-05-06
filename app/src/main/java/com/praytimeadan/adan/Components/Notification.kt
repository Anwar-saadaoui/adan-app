package com.praytimeadan.adan.Components

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.praytimeadan.adan.ui.MainActivity

@SuppressLint("ObsoleteSdkInt")
class Notification(
    context: Context,
    title: String,
    text: String,
    icon: Int,
    notificationName: String
) {
    private val NOTIFICATION_CHANEL_ID = "notificationChanel"
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        // notification chanel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChanel = NotificationChannel(
                NOTIFICATION_CHANEL_ID,
                notificationName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChanel)

            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context,0,intent,
                PendingIntent.FLAG_IMMUTABLE)
            val notificationBuilder = NotificationCompat.Builder(context,NOTIFICATION_CHANEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(icon)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            notificationManager.notify(1, notificationBuilder.build())
        }
    }
}