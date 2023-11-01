package com.velogm.data_local.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.velogm.data.datasource.SharedPreferencesDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesDataSourceImpl @Inject constructor(
    private val prefs: SharedPreferences,
) : SharedPreferencesDataSource {
    override var accessToken: String?
        get() = prefs.getString("AccessToken", null)
        set(value) = prefs.edit { putString("AccessToken", value) }

    override var refreshToken: String?
        get() = prefs.getString("RefreshToken", null)
        set(value) = prefs.edit { putString("RefreshToken", value) }

    override var checkLogin: Boolean
        get() = prefs.getBoolean("CheckLogin", false)
        set(value) = prefs.edit { putBoolean("CheckLogin", value) }

    override var withdrawal: Boolean
        get() = prefs.getBoolean("CheckWithdrawal", false)
        set(value) = prefs.edit { putBoolean("CheckWithdrawal", value) }

}

