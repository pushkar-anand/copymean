package me.pushkaranand.copymeanx.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import me.pushkaranand.copymeanx.utils.ClipboardHelper

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (Intent.ACTION_BOOT_COMPLETED == action) {
            restartServices(context)
        }
    }

    private fun restartServices(context: Context) {
        starClipboardManager(context)
    }

    private fun starClipboardManager(context: Context) {
        if (ClipboardHelper.isClipboardMonitoringSettingsOn(context)) {
            ClipboardHelper.startClipboardMonitoring(context)
        }
    }
}
