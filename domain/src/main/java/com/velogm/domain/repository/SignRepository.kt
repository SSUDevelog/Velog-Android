package com.velogm.domain.repository

import com.velogm.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface SignRepository {
    suspend fun getGoogleLogin(code: String): Flow<Token>
}