package com.data_remote.api

import com.velogm.data.dto.response.TokenResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SignApiService {

    companion object {
        const val SIGN_API = "sign-api"
        const val GOOGLE_LOGIN = "google-login"
        const val CODE = "code"
    }

    @GET("/$SIGN_API/$GOOGLE_LOGIN")
    suspend fun getGoogleLogin(
        @Query(CODE) code: String
    ): TokenResponseDto
}