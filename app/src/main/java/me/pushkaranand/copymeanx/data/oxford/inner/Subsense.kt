package me.pushkaranand.copymeanx.data.oxford.inner

data class Subsense(
    val definitions: ArrayList<String>,
    val examples: ArrayList<Example>,
    val id: String,
    val shortDefinitions: ArrayList<String>,
    val thesaurusLinks: ArrayList<ThesaurusLink>
)