package me.pushkaranand.copymeanx.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import me.pushkaranand.copymeanx.R

object Notifications {

    const val CLIPBOARD_STICKY_ID = 101
    private const val CHANNEL_STICKY_ID = "sticky-channel"


    @RequiresApi(Build.VERSION_CODES.O)
    private fun stickyNotificationChannel(context: Context): NotificationChannel {

        val name = context.getString(R.string.notification_channel_sticky_name)
        val description = context.getString(R.string.notification_channel_sticky_description)
        val importance = NotificationManager.IMPORTANCE_MIN

        return NotificationChannel(CHANNEL_STICKY_ID, name, importance).apply {
            this.description = description
        }
    }

    fun getClipboardStickyNotification(context: Context): Notification {
        val applicationContext = context.applicationContext
        val notificationManager: NotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                stickyNotificationChannel(
                    applicationContext
                )
            )
        }
        val title = applicationContext.getString(R.string.notification_clipboard_sticky_title)
        val content = applicationContext.getString(R.string.notification_clipboard_sticky_content)

        val mBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_STICKY_ID)
            .setSmallIcon(R.drawable.ic_menu_home) // TODO update icon
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_MIN)

        return mBuilder.build()
    }

}