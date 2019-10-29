package me.pushkaranand.copymeanx.api

object Urls {

    private const val PROTOCOL = "https"
    private const val SEPERATOR = "://"
    private const val DOMAIN = "dictionaryapi.com"
    private const val API_VERSION = "v3"
    private const val COLLEGIATE_API = "collegiate"
    private const val LEARNERS_API = "learners"

    const val BASE_URL = "$PROTOCOL$SEPERATOR$DOMAIN/api/$API_VERSION/references/"

    const val COLLEGIATE_URL = "$BASE_URL$COLLEGIATE_API/json/"
    const val LEARNERS_URL = "$BASE_URL$LEARNERS_API/json/"
}