package com.data_remote.api

import com.velogm.data.dto.response.WithdrawalDto
import com.velogm.data.dto.response.TokenResponseDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SignApiService {
    @GET("/sign-api/google-login")
    suspend fun getGoogleLogin(
        @Query("code") code: String
    ): TokenResponseDto

    @POST("/sign-api/sign-out")
    suspend fun postWithdrawal(): WithdrawalDto
}