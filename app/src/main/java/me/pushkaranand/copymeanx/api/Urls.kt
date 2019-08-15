package me.pushkaranand.copymeanx.api

class Urls {

    companion object{

        private const val PROTOCOL = "https"
        private const val SEPERATOR = "://"
        private const val DOMAIN = "od-api.oxforddictionaries.com"
        private const val API_ROUTE = "api"

        const val BASE_URL = "$PROTOCOL$SEPERATOR$DOMAIN/$API_ROUTE"

        const val API_VERSION = "v2"
    }
}