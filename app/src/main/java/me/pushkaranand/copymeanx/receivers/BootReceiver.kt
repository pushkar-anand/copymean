package me.pushkaranand.copymeanx.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (Intent.ACTION_BOOT_COMPLETED == action) {
            restartServices()
        }
    }

    private fun restartServices() {
        // TODO start clipboardManager
    }
}
