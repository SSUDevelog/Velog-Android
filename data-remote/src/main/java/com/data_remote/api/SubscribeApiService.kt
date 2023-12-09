package com.data_remote.api

import com.velogm.data.dto.response.DeleteFollowerDto
import com.velogm.data.dto.response.FollowerDto
import com.velogm.data.dto.response.InputFollowerDto
import com.velogm.data.dto.response.PostFollowListDto
import com.velogm.data.dto.response.PostListDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SubscribeApiService {
    companion object {
        const val SUBSCRIBE = "subscribe"
        const val TREND_POSTS = "trendposts"
        const val SUBSCRIBER_POSTS = "subscriberpost"
        const val GET_SUBSCRIBER = "getsubscriber"
        const val UNSUBSCRIBE = "unsubscribe"
        const val TARGET_NAME = "targetName"
        const val INPUT_NAME = "inputname"
        const val NAME = "name"
        const val ADD_SUBSCRIBER = "addsubscriber"
        const val FCM_TOKEN = "fcmToken"
    }

    @GET("/$SUBSCRIBE/$TREND_POSTS")
    suspend fun getTrendPost(
    ): PostListDto

    @GET("/$SUBSCRIBE/$SUBSCRIBER_POSTS")
    suspend fun getFollowPost(
    ): PostFollowListDto

    @GET("/$SUBSCRIBE/$GET_SUBSCRIBER")
    suspend fun getFollower(
    ): List<FollowerDto>

    @DELETE("/$SUBSCRIBE/$UNSUBSCRIBE/{$TARGET_NAME}")
    suspend fun deleteFollower(
        @Path(value = TARGET_NAME) followerName: String
    ): DeleteFollowerDto

    @GET("/$SUBSCRIBE/$INPUT_NAME/{$NAME}")
    suspend fun getInputFollower(
        @Path(value = NAME) followerName: String?
    ): InputFollowerDto

    @POST("/$SUBSCRIBE/$ADD_SUBSCRIBER")
    suspend fun postAddFollower(
        @Query(FCM_TOKEN) token: String,
        @Query(NAME) followerName: String
    ): String
}