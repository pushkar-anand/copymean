package me.pushkaranand.copymeanx.data

import android.content.Context
import me.pushkaranand.copymeanx.api.Urls
import me.pushkaranand.copymeanx.api.WebsterDictionary
import retrofit2.Retrofit

class DataRepository() {

    private var websterDictionary: WebsterDictionary

    init {
        System.loadLibrary("native-lib")
        val retrofit = Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .build()

        websterDictionary = retrofit.create(WebsterDictionary::class.java)
    }

    companion object {

        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(context: Context): DataRepository? {
            return instance ?: synchronized(DataRepository::class.java) {
                if (instance == null) {
                    instance = DataRepository()
                }
                return instance
            }
        }
    }

    private external fun getAppID(): String
    private external fun getAppKey(): String
}