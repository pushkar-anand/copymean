package me.pushkaranand.copymeanx.data.oxford.inner

data class Result(

    val id: String,
    val language: String,
    val lexicalEntries: ArrayList<LexicalEntry>,
    val type: String,
    val word: String

)
