package me.pushkaranand.copymeanx.services

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.preference.PreferenceManager
import me.pushkaranand.copymeanx.R
import me.pushkaranand.copymeanx.utils.ClipboardHelper
import me.pushkaranand.copymeanx.utils.Notifications


class ClipboardMonitor : Service() {

    companion object {
        var isMonitoringOn = false
    }

    private lateinit var clipboardManager: ClipboardManager

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        bringServiceToForeground()
        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (!isMonitoringOn && clipboardMonitoringSettingsOn()) {
            startClipboardMonitoring()
        }
    }

    private fun bringServiceToForeground() {
        startForeground(
            Notifications.CLIPBOARD_STICKY_ID,
            Notifications.getClipboardStickyNotification(this)
        )
    }

    private fun clipboardMonitoringSettingsOn(): Boolean {
        val prefManager = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        return prefManager.getBoolean(getString(R.string.key_clipboard_monitor), false)
    }

    private val clipChangedListener = ClipboardManager.OnPrimaryClipChangedListener {
        ClipboardHelper.handleClipData(clipboardManager.primaryClip)
        isMonitoringOn = true
    }

    private fun startClipboardMonitoring() {
        clipboardManager.addPrimaryClipChangedListener(clipChangedListener)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onDestroy() {
        if (isMonitoringOn) {
            clipboardManager.removePrimaryClipChangedListener(clipChangedListener)
            isMonitoringOn = false
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(true)
        }
        super.onDestroy()
    }
}
