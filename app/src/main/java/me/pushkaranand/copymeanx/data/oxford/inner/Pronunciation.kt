package me.pushkaranand.copymeanx.data.oxford.inner

data class Pronunciation(
    val audioFile: String?,
    val dialects: ArrayList<String>,
    val phoneticNotation: String,
    val phoneticSpelling: String
)