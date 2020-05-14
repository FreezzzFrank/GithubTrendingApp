package com.example.trendinggit

import android.app.Application

class APP : Application() {

    companion object {
        lateinit var instance: APP
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}