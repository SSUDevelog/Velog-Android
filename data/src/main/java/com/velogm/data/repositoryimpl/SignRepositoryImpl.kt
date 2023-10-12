package com.velogm.data.repositoryimpl

import com.velogm.data.datasource.SignDataSource
import com.velogm.data.dto.response.toTokenEntity
import com.velogm.domain.model.Token
import com.velogm.domain.repository.SignRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignRepositoryImpl @Inject constructor(
    private val signDataSource: SignDataSource
) : SignRepository {
    override suspend fun getGoogleLogin(code: String): Flow<Token> {
        return flow {
            val result = kotlin.runCatching {
                signDataSource.getGoogleLogin(code).toTokenEntity()
            }
            emit(result.getOrDefault(Token("")))
        }
    }
}