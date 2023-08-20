package com.data_remote.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TagApiService {
    @GET("/tag/gettag")
    suspend fun getTag(
    ):List<String>

    @GET("/tag/popularpost")
    suspend fun getPopularTag(
    ):List<String>

    @DELETE("/tag/deletetag")
    suspend fun deleteTag(
        @Query("tag") tag:String
    ):String

    @POST("/tag/addtag")
    suspend fun addTag(
        @Query("tag") tag:String
    ):String

}