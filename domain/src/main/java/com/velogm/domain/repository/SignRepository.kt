package com.velogm.domain.repository

import com.velogm.domain.model.Withdrawal
import com.velogm.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface SignRepository {
    suspend fun getGoogleLogin(code: String): Flow<Token>
    suspend fun postWithdrawal(): Flow<Withdrawal>
}