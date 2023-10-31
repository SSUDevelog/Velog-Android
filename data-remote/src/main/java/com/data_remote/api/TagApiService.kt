package com.data_remote.api

import com.velogm.data.dto.response.TrendPostDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TagApiService {

    companion object {
        const val TAG = "tag"
        const val GET_TAG = "gettag"
        const val POPULAR_POST = "popularpost"
        const val DELETE_TAG = "deletetag"
        const val ADD_TAG = "addtag"
        const val GET_TAG_POST = "gettagpost"
    }

    @GET("/$TAG/$GET_TAG")
    suspend fun getTag(
    ): List<String>

    @GET("/$TAG/$POPULAR_POST")
    suspend fun getPopularTag(
    ): List<String>

    @DELETE("/$TAG/$DELETE_TAG")
    suspend fun deleteTag(
        @Query(TAG) tag: String
    ): String

    @POST("/$TAG/$ADD_TAG")
    suspend fun addTag(
        @Query(TAG) tag: String
    ): String

    @GET("/$TAG/$GET_TAG_POST")
    suspend fun getTagPost(
        @Query(TAG) tag: String
    ): List<TrendPostDto>
}