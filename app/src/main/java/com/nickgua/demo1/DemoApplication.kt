package com.nickgua.demo1

import android.app.Application
import android.content.Context

class DemoApplication : Application() {
    companion object {
        lateinit var appContext: Context
    }

    init {
        appContext = this
    }

    override fun onCreate() {
        super.onCreate()
        PreferenceUtil.init(this)
    }
}