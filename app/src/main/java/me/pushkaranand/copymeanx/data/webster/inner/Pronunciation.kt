package me.pushkaranand.copymeanx.data.webster.inner

import com.squareup.moshi.Json

class Pronunciation(
    val sound: Sound?,
    @field:Json(name = "mw") val pronunciationMWFormat: String?
)