package com.velogandroid

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class VelogMobileApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setTimber()
        setDarkMode()
    }

    private fun setTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}