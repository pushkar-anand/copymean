package me.pushkaranand.copymeanx.data

import android.content.Context

class DataRepository() {

    companion object {

        init {
            System.loadLibrary("native-lib")
        }

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