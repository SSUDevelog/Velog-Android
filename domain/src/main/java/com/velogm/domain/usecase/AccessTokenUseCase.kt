package com.velogm.domain.usecase

import com.velogm.domain.model.Token
import com.velogm.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AccessTokenUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(code: String): Flow<Token> =
        repository.getGoogleLogin(code)
}