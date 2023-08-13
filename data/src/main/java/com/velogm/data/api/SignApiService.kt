package com.velogm.data.api

import com.velogm.data.dto.response.TokenResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SignApiService {
    @GET("/sign-api/google-login")
    suspend fun getGoogleLogin(
        @Query("code") code:String
    ):TokenResponseDto
}