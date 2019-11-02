package me.pushkaranand.copymeanx.utils

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
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
        return Intent(context, ClipboardMonitor::class.java)
    }

    fun startClipboardMonitoring(context: Context) {
        if (!ClipboardMonitor.isMonitoringOn) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(getClipboardServiceIntent(context))
            } else {
                context.startService(getClipboardServiceIntent(context))
            }
        }
    }

    fun stopClipboardMonitoring(context: Context) {
        context.stopService(getClipboardServiceIntent(context))
    }


}