package com.data_remote.api

import retrofit2.http.GET

interface TagApiService {
    @GET("/tag/gettag")
    suspend fun getTag(
    ):List<String>

    @GET("/tag/popularpost")
    suspend fun getPopularTag(
    ):List<String>
}