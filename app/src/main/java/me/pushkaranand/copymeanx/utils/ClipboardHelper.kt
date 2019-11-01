package me.pushkaranand.copymeanx.utils

import android.content.ClipData
import android.util.Log

object ClipboardHelper {

    var lastCopiedData = ""
    private const val TAG = "CLIPBOARD HELPER"
    
    fun handleClipData(clipData: ClipData?) {
        if (clipData != null) {
            val copiedText = clipData.getItemAt(0).text.toString().trim()
            if (copiedText.isNotEmpty() && lastCopiedData != copiedText) {
                Log.d(TAG, "Copied: $copiedText")
                lastCopiedData = copiedText
            }
        } else {
            Log.d(TAG, "clipData is null")
        }
    }


}