package com.velogm.data

import com.velogm.domain.SharedPreferenceToken
import okhttp3.Interceptor
import timber.log.Timber
import javax.inject.Inject


class TokenInterceptor @Inject constructor(
    private val token: SharedPreferenceToken
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var accessToken = token.token
        val request = chain.request().newBuilder()
            .addHeader("X-AUTH-TOKEN", "$accessToken").build()
        Timber.tag("intercept").d(accessToken)
        val result = chain.proceed(request)
        //refresh가 없는디요 ???
        when (result.code) {
            401 -> {
                try {
                    Timber.e("액세스 토큰 만료, 토큰 재발급 합니다.")
                    token.token = ""
//                    token.checkLogin=false
                    Timber.e("액세스 토큰 만료, ${token.token}")
//                    result.close()
                    return result
                } catch (t: Throwable) {
                    accessToken = ""
                }
            }
        }
        return result
    }
}

