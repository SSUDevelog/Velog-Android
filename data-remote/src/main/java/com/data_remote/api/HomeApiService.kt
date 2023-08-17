package com.data_remote.api

import retrofit2.http.GET

interface HomeApiService {
    @GET("/tag/gettag")
    suspend fun getTag(
    ):List<String>
}