package com.velogm.domain.repository

interface AuthRepository {
    fun saveAccessToken(a: String)
    fun getAccessToken(): String
    fun saveRefreshToken(b: String)
    fun getRefreshToken(): String
    fun checkLogin(): Boolean
    fun saveCheckLogin(checkLogin: Boolean)
    fun getWithdrawal(): Boolean
    fun saveWithdrawal(checkWithdrawal: Boolean)

}