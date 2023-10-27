package com.velogm.data.repositoryimpl

import com.velogm.data.datasource.SharedPreferencesDataSource
import com.velogm.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPrefDataSource: SharedPreferencesDataSource
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

    override fun checkLogin(): Boolean {
        return sharedPrefDataSource.checkLogin
    }

    override fun saveCheckLogin(checkLogin: Boolean) {
        sharedPrefDataSource.checkLogin = checkLogin
    }

    override fun getWithdrawal(): Boolean {
        return sharedPrefDataSource.withdrawal
    }

    override fun saveWithdrawal(checkWithdrawal: Boolean) {
        sharedPrefDataSource.withdrawal = checkWithdrawal
    }


}