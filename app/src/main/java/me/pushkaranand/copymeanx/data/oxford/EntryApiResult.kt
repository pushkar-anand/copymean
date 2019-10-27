package me.pushkaranand.copymeanx.data.oxford

import com.squareup.moshi.Json
import me.pushkaranand.copymeanx.data.oxford.inner.MetaData
import me.pushkaranand.copymeanx.data.oxford.inner.Result

data class EntryApiResult(

    val id: String,
    @Json(name = "metadata") val metaData: MetaData,
    val results: ArrayList<Result>,
    val word: String

)