package com.velogm.data.datasource

import com.velogm.data.dto.response.DeleteFollowerDto
import com.velogm.data.dto.response.FollowerDto
import com.velogm.data.dto.response.InputFollowerDto
import com.velogm.data.dto.response.PostFollowListDto
import com.velogm.data.dto.response.PostListDto

interface SubscribeDataSource {
    suspend fun getTrendPost(): PostListDto

    suspend fun getFollowPost(): PostFollowListDto

    suspend fun getFollower(): List<FollowerDto>

    suspend fun deleteFollower(followerName: String): DeleteFollowerDto

    suspend fun getInputFollower(followerName: String?): InputFollowerDto

    suspend fun postAddFollower(followerName: String, fcmToken: String): String
}