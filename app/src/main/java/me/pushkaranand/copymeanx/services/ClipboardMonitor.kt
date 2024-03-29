package me.pushkaranand.copymeanx.services

import android.app.Service
import android.content.ClipDescription
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
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
        return ClipboardHelper.isClipboardMonitoringSettingsOn(applicationContext)
    }

    private val clipChangedListener = ClipboardManager.OnPrimaryClipChangedListener {
        Log.d(javaClass.simpleName, "PrimaryClipChanged")
        for (i in 0 until clipboardManager.primaryClipDescription!!.mimeTypeCount) {
            Log.d(
                javaClass.simpleName,
                "MIME: " + clipboardManager.primaryClipDescription!!.getMimeType(i)
            )
        }

        val isTextData =
            clipboardManager.primaryClipDescription!!.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) ||
                    clipboardManager.primaryClipDescription!!.hasMimeType(ClipDescription.MIMETYPE_TEXT_HTML)
        if (isTextData) {
            ClipboardHelper.handleClipData(clipboardManager.primaryClip)
        } else {
            Log.d(this.javaClass.simpleName, "Clipboard doesn't have text data.")
        }
    }

    private fun startClipboardMonitoring() {
        isMonitoringOn = true
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
