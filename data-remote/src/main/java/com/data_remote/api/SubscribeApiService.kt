package com.data_remote.api

import com.velogm.data.dto.response.PostListDto
import retrofit2.http.GET

interface SubscribeApiService {

    @GET("/subscribe/trendposts")
    suspend fun getTrendPost(
    ): PostListDto
}