package me.pushkaranand.copymeanx.data.oxford.inner

class LexicalEntry(
    val derivatives: ArrayList<Derivative>,
    val entries: ArrayList<Entry>,
    val language: String,
    val lexicalCategory: LexicalCategory,
    val pronunciations: ArrayList<Pronunciation>,
    val text: String
)