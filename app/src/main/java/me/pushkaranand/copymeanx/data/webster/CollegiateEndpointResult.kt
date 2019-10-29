package me.pushkaranand.copymeanx.data.webster

import com.squareup.moshi.Json
import me.pushkaranand.copymeanx.data.webster.inner.HeadwordInfo
import me.pushkaranand.copymeanx.data.webster.inner.Meta

data class CollegiateEndpointResult(
    val meta: Meta?,
    @field:Json(name = "hwi") val headwordInfo: HeadwordInfo?,
    val functionalLabel: String?,
    val shortDefinitions: List<String>?,
    val date: String?
)