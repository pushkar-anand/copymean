package me.pushkaranand.copymeanx.utils

import android.app.Application

private const val PREF_NAME = "first_run"
private const val FIRST_RUN_KEY = "first_run"

class FirstRunHelper(application: Application) {

    private val preferenceHelper = PreferenceHelper(application, PREF_NAME)

    fun isFirstRun(): Boolean {
        return !preferenceHelper.keyExists(FIRST_RUN_KEY)
    }

    fun firstRunDone() {
        preferenceHelper.setValue(FIRST_RUN_KEY, true)
    }

}