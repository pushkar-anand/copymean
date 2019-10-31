package me.pushkaranand.copymeanx.data

import android.content.Context
import me.pushkaranand.copymeanx.api.WebsterDictionary


class DataRepository {

    private val websterDictionary = WebsterDictionary.create()

    companion object {

        init {
            System.loadLibrary("native-lib")
        }

        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(context: Context): DataRepository {
            return instance ?: synchronized(DataRepository::class.java) {
                if (instance == null) {
                    instance = DataRepository()
                }
                return instance!!
            }
        }
    }

    private external fun getCollegiateAPIKey(): String
    private external fun getLearnersAPIKey(): String
}