package com.velogm.data.datasource

import com.velogm.data.dto.response.TokenResponseDto

interface SignDataSource {
    suspend fun getGoogleLogin(code:String): TokenResponseDto
}