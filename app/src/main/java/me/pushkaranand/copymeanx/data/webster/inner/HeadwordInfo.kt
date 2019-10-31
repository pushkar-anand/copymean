package me.pushkaranand.copymeanx.data.webster.inner

import com.squareup.moshi.Json

data class HeadwordInfo(
    @field:Json(name = "hw") val headword: String?,
    @field:Json(name = "prs") val pronunciations: List<Pronunciation>?
)