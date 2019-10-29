package me.pushkaranand.copymeanx.api

object Urls {

    private const val PROTOCOL = "https"
    private const val SEPERATOR = "://"
    private const val DOMAIN = "dictionaryapi.com"
    private const val API_VERSION = "v3"
    private const val COLLEGIATE_API = "collegiate"
    private const val LEARNERS_API = "learners"

    const val BASE_URL = "$PROTOCOL$SEPERATOR$DOMAIN/api/$API_VERSION/references/"

    private fun learnersAPIUrl() = "$BASE_URL$LEARNERS_API/json/"
    private fun collegiateAPIUrl() = "$BASE_URL$COLLEGIATE_API/json/"

    const val TYPE_COLLEGIATE = 100
    const val TYPE_LEARNERS = 200


    fun buildURL(word: String, type: Int): String {
        val url: String = when (type) {
            TYPE_COLLEGIATE ->
                collegiateAPIUrl() + "/"
            TYPE_LEARNERS ->
                learnersAPIUrl() + "/"
            else -> throw Exception("Invalid type supplied")
        }
        return url + word
    }

}