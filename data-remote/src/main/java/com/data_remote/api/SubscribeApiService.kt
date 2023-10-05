package com.data_remote.api

import com.velogm.data.dto.response.DeleteFollowerDto
import com.velogm.data.dto.response.FollowerDto
import com.velogm.data.dto.response.InputFollowerDto
import com.velogm.data.dto.response.PostListDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SubscribeApiService {

    @GET("/subscribe/trendposts")
    suspend fun getTrendPost(
    ): PostListDto

    @GET("/subscribe/subscriberpost")
    suspend fun getFollowPost(
    ): PostListDto

    @GET("/subscribe/getsubscriber")
    suspend fun getFollower(
    ): List<FollowerDto>

    @DELETE("/subscribe/unsubscribe/{targetName}")
    suspend fun deleteFollower(
        @Path(value = "targetName") followerName: String
    ): DeleteFollowerDto

    @GET("/subscribe/inputname/{name}")
    suspend fun getInputFollower(
        @Path(value = "name") followerName: String?
    ): InputFollowerDto

    @POST("/subscribe/addsubscriber")
    suspend fun postAddFollower(
        @Query("fcmToken") token: String,
        @Query("name") followerName: String
    ): String
}