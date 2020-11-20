package neel.com.fatimahifzulquran.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import neel.com.fatimahifzulquran.R
import neel.com.fatimahifzulquran.util.sendNotification


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Log.d("on_receive",intent.data.toString())

        // TODO: Step 1.9 add call to sendNotification
        val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
                context.getText(R.string.test_notification).toString(),
                context
        )
    }
}