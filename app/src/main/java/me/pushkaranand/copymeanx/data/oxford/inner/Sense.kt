package me.pushkaranand.copymeanx.data.oxford.inner

class Sense(
    val definitions: ArrayList<String>,
    val examples: ArrayList<Example>,
    val id: String,
    val notes: ArrayList<Note>?,
    val shortDefinitions: ArrayList<String>,
    val subsenses: ArrayList<Subsense>?,
    val thesaurusLinks: ArrayList<ThesaurusLink>

)