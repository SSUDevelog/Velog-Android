package com.velogm.data.repositoryimpl

import com.velogm.data.datasource.SharedPreferencesDataSource
import com.velogm.data.datasource.SignDataSource
import com.velogm.data.dto.response.toTokenEntity
import com.velogm.domain.model.Token
import com.velogm.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPrefDataSource: SharedPreferencesDataSource,
    private val signDataSource: SignDataSource
    ):AuthRepository {
    override fun saveAccessToken(a: String) {
       sharedPrefDataSource.accessToken=a
    }

    override fun getAccessToken(): String {
        return sharedPrefDataSource.accessToken ?:""
    }

    override fun saveRefreshToken(b: String) {
        sharedPrefDataSource.refreshToken=b
    }
    override fun getRefreshToken(): String {
        return sharedPrefDataSource.refreshToken ?:""
    }

    override suspend fun getGoogleLogin(code: String): Flow<Token> {
        return flow{
           val result = kotlin.runCatching {
               signDataSource.getGoogleLogin(code).toTokenEntity()
           }
            emit(result.getOrDefault(Token("")))
        }
    }

}