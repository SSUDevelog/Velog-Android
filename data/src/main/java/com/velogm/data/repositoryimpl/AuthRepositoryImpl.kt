package com.velogm.data.repositoryimpl

import com.velogm.domain.SharedPreferenceToken
import com.velogm.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPrefDataSource: SharedPreferenceToken
) : AuthRepository {
    override fun saveAccessToken(a: String) {
        sharedPrefDataSource.token = a
    }

    override fun getAccessToken(): String {
        return sharedPrefDataSource.token ?: ""
    }

    override fun checkLogin(): Boolean {
        return sharedPrefDataSource.checkLogin
    }

    override fun saveCheckLogin(checkLogin: Boolean) {
        sharedPrefDataSource.checkLogin = checkLogin
    }

    override fun getWithdrawal(): Boolean {
        return sharedPrefDataSource.checkLogin
    }

    override fun saveWithdrawal(checkWithdrawal: Boolean) {
        sharedPrefDataSource.checkLogin = checkWithdrawal
    }


}