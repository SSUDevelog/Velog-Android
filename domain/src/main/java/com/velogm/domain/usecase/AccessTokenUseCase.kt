package com.velogm.domain.usecase

import com.velogm.domain.model.Token
import com.velogm.domain.repository.SignRepository
import kotlinx.coroutines.flow.Flow

class AccessTokenUseCase(
    private val repository: SignRepository
) {
    suspend operator fun invoke(code: String): Flow<Token> =
        repository.getGoogleLogin(code)
}