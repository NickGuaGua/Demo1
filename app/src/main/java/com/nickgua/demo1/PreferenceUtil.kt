package com.nickgua.demo1

import android.content.Context
import android.content.SharedPreferences

object PreferenceUtil {

    private const val PREFERENCE_NAME = "demo_share_preference"
    private const val KEY_FIRST_LAUNCH = "key_first_launch"

    private lateinit var preferences: SharedPreferences

    var isFirstLaunch: Boolean
        get() {
            val result = preferences.getBoolean(KEY_FIRST_LAUNCH, true)
            isFirstLaunch = false
            return result
        }
        private set(value) {
            preferences.edit()?.putBoolean(KEY_FIRST_LAUNCH, value)?.apply()
        }

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}