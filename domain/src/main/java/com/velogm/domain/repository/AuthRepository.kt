package com.velogm.domain.repository

interface AuthRepository {

    fun saveAccessToken(a:String)
    fun getAccessToken():String
    fun saveRefreshToken(b:String)
    fun getRefreshToken():String
}