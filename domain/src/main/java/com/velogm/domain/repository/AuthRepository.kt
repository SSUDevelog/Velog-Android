package com.velogm.domain.repository

import com.velogm.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun saveAccessToken(a: String)
    fun getAccessToken(): String
    fun saveRefreshToken(b: String)
    fun getRefreshToken(): String
    suspend fun getGoogleLogin(code: String): Flow<Token>
}