package com.velogm.data.repositoryimpl

import com.velogm.data.datasource.SignDataSource
import com.velogm.data_local.datasource.SharedPreferencesDataSource
import com.velogm.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPrefDataSource: SharedPreferencesDataSource,
) : AuthRepository {
    override fun saveAccessToken(a: String) {
        sharedPrefDataSource.accessToken = a
    }

    override fun getAccessToken(): String {
        return sharedPrefDataSource.accessToken ?: ""
    }

    override fun saveRefreshToken(b: String) {
        sharedPrefDataSource.refreshToken = b
    }

    override fun getRefreshToken(): String {
        return sharedPrefDataSource.refreshToken ?: ""
    }


}