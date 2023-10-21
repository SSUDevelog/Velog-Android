package com.velogm.data_local.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.velogm.domain.SharedPreferenceToken
import javax.inject.Inject

class TokenImpl @Inject constructor(
    private val prefs: SharedPreferences,
) : SharedPreferenceToken {
    override var token: String
        get() = prefs.getString("AccessToken", "")?:""
        set(value) = prefs.edit { putString("AccessToken", value) }

    override var checkLogin : Boolean
        get() = prefs.getBoolean("CheckLogin",false)
        set(value) = prefs.edit{putBoolean("CheckLogin",value)}

}