package neel.com.fatimahifzulquran.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import neel.com.fatimahifzulquran.MainActivity
import neel.com.fatimahifzulquran.R


private val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // TODO: Step 1.11 create intent
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    // TODO: Step 1.12 create PendingIntent
    val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
    )

    /**
    Step 1.2 get an instance of NotificationCompat.Builder
     */
    // Build the notification
    val builder = NotificationCompat.Builder(
            applicationContext,
            applicationContext.getString(R.string.computer_test_notification_channel_id)
    )
            /**
             *  Step 1.3 set title, text and icon to builder
             */
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(applicationContext
                    .getString(R.string.notification_title))
            .setContentText(messageBody)
            // TODO: Step 1.13 set content intent
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)

/**
    Step 1.4 call notify
    */
    notify(NOTIFICATION_ID, builder.build())

}

/**
 * Cancels all notifications.
 *
 */
fun NotificationManager.cancelNotifications() {
    cancelAll()
}
