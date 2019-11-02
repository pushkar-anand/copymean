package me.pushkaranand.copymeanx.utils

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.preference.PreferenceManager
import me.pushkaranand.copymeanx.R
import me.pushkaranand.copymeanx.services.ClipboardMonitor

object ClipboardHelper {

    private var lastCopiedData = ""
    private const val TAG = "CLIPBOARD HELPER"

    fun handleClipData(clipData: ClipData?) {
        if (clipData != null) {
            val copiedText = clipData.getItemAt(0).text.toString().trim()
            if (copiedText.isNotEmpty() && lastCopiedData != copiedText) {
                Log.d(TAG, "Copied: $copiedText")
                lastCopiedData = copiedText
                handleCopiedText(copiedText)
            }
        } else {
            Log.d(TAG, "clipData is null")
        }
    }

    private fun handleCopiedText(copiedText: String) {
        val regex = Regex("[a-zA-Z]+")
        if (copiedText.matches(regex)) {
            // TODO get meaning an display bubble.
        }
    }

    private fun getClipboardServiceIntent(context: Context): Intent {
        val applicationContext = context.applicationContext
        return Intent(applicationContext, ClipboardMonitor::class.java)
    }

    fun startClipboardMonitoring(context: Context) {
        val applicationContext = context.applicationContext
        if (!ClipboardMonitor.isMonitoringOn) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                applicationContext.startForegroundService(
                    getClipboardServiceIntent(applicationContext)
                )
            } else {
                applicationContext.startService(
                    getClipboardServiceIntent(applicationContext)
                )
            }
        }
    }

    fun stopClipboardMonitoring(context: Context) {
        val applicationContext = context.applicationContext
        context.stopService(getClipboardServiceIntent(applicationContext))
    }

    fun isClipboardMonitoringSettingsOn(context: Context): Boolean {
        val applicationContext = context.applicationContext
        val prefManager = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        return prefManager.getBoolean(
            applicationContext.getString(R.string.key_clipboard_monitor),
            false
        )
    }


}