package me.pushkaranand.copymeanx.utils

import android.app.Application
import android.content.Context

class PreferenceHelper(application: Application, prefName: String) {

    private val sharedPreference =
        application.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    fun setValue(key: String, value: String) {
        val prefEditor = sharedPreference.edit()
        prefEditor.putString(key, value)
        prefEditor.apply()
    }

    fun setValue(key: String, value: Int) {
        val prefEditor = sharedPreference.edit()
        prefEditor.putInt(key, value)
        prefEditor.apply()
    }

    fun setValue(key: String, value: Boolean) {
        val prefEditor = sharedPreference.edit()
        prefEditor.putBoolean(key, value)
        prefEditor.apply()
    }

    fun keyExists(key: String): Boolean {
        return sharedPreference.contains(key)
    }

    fun getIntValue(key: String): Int {
        return sharedPreference.getInt(key, 0)
    }

    fun getBoolValue(key: String): Boolean {
        return sharedPreference.getBoolean(key, false)
    }

    fun getStringValue(key: String): String? {
        return sharedPreference.getString(key, "")
    }

}