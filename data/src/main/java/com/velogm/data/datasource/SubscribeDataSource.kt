package com.velogm.data.datasource

import com.velogm.data.dto.response.DeleteFollowerDto
import com.velogm.data.dto.response.FollowerDto
import com.velogm.data.dto.response.InputFollowerDto
import com.velogm.data.dto.response.PostListDto

interface SubscribeDataSource {
    suspend fun getTrendPost(): PostListDto

    suspend fun getFollowPost(): PostListDto

    suspend fun getFollower(): List<FollowerDto>

    suspend fun deleteFollower(followerName: String): DeleteFollowerDto

    suspend fun getInputFollower(followeNmae: String?): InputFollowerDto

    suspend fun postaddFollower(followerName: String): String
}