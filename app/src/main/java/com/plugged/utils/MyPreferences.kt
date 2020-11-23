package com.plugged.utils

import android.content.Context
import androidx.preference.PreferenceManager

class MyPreferences  (context: Context?) {

    companion object {
        private const val PLUGGED_APP = "Plugged"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var logged_in = preferences.getBoolean(PLUGGED_APP, false)
        set(value) = preferences.edit().putBoolean(PLUGGED_APP, value).apply()

    var is_staff = preferences.getBoolean(PLUGGED_APP, false)
        set(value) = preferences.edit().putBoolean(PLUGGED_APP, value).apply()
}