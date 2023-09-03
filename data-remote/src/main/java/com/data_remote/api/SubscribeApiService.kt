package com.data_remote.api

import com.velogm.data.dto.response.DeleteFollowerDto
import com.velogm.data.dto.response.FollowerDto
import com.velogm.data.dto.response.PostListDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

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
}