package com.velogm.data.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesDataSource @Inject constructor(
    private val prefs: SharedPreferences,
) {
    var accessToken: String?
        get() = prefs.getString("AccessToken", null)
        set(value) = prefs.edit { putString("AccessToken", value) }

    var refreshToken: String?
        get() = prefs.getString("RefreshToken", null)
        set(value) = prefs.edit { putString("RefreshToken", value) }

}