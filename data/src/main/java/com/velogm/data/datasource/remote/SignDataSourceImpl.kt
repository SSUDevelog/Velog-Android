package com.velogm.data.datasource.remote

import com.velogm.data.api.SignApiService
import com.velogm.data.datasource.SignDataSource
import com.velogm.data.dto.response.TokenResponseDto
import javax.inject.Inject

class SignDataSourceImpl @Inject constructor(
    private val apiService: SignApiService
):SignDataSource{
    override suspend fun getGoogleLogin(code: String): TokenResponseDto {
        return apiService.getGoogleLogin(code)
    }

}