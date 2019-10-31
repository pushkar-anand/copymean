package me.pushkaranand.copymeanx.data.webster.inner

import java.util.*

data class Sound(
    val audio: String
) {
    fun getAudioUrl(): String {
        return "https://media.merriam-webster.com/soundc11/${getSubdirectory()}/${audio}.wav"
    }

    private fun getSubdirectory(): String {
        val smallCaseStr = audio.toLowerCase(Locale.getDefault())

        return if ("bix" in smallCaseStr) {
            "bix"
        } else if ("gg" in smallCaseStr) {
            "gg"
        } else if ((smallCaseStr.first()).isDigit() || !(smallCaseStr.first()).isLetterOrDigit()) {
            "number"
        } else {
            (smallCaseStr.first()).toString()
        }

    }


}