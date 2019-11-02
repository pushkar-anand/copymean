package me.pushkaranand.copymeanx.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import me.pushkaranand.copymeanx.receivers.AlarmReceiver
import java.util.*

class AlarmHelper(context: Context) {

    private val applicationContext: Context = context.applicationContext
    private var alarmManager: AlarmManager

    init {
        alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    companion object {
        const val DAILY_ALARM_PENDING_INTENT_CODE = 200
    }

    private fun getDailyAlarmPendingIntent(): PendingIntent {
        val intent = Intent(
            applicationContext,
            AlarmReceiver::class.java
        )

        return PendingIntent.getBroadcast(
            applicationContext,
            DAILY_ALARM_PENDING_INTENT_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun getTimeToAlarmInMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 12)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        return calendar.timeInMillis
    }

    fun setDailyAlarm() {
        alarmManager.setInexactRepeating(
            AlarmManager.RTC,
            getTimeToAlarmInMillis(),
            AlarmManager.INTERVAL_DAY,
            getDailyAlarmPendingIntent()
        )
    }

    fun disableDailyAlarm() {
        alarmManager.cancel(
            getDailyAlarmPendingIntent()
        )
    }


}