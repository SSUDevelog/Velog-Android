package com.velogm.data

import com.velogm.domain.SharedPreferenceToken
import okhttp3.Interceptor
import timber.log.Timber
import javax.inject.Inject


class TokenInterceptor@Inject constructor(
    private val authRepository: SharedPreferenceToken
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val accessToken = authRepository.token
        val request = chain.request().newBuilder()
            .addHeader("X-AUTH-TOKEN", "$accessToken").build()
        Timber.tag("intercept").d(accessToken)
        return chain.proceed(request)
    }
}

