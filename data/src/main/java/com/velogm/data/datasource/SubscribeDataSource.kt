package com.velogm.data.datasource

import com.velogm.data.dto.response.FollowerDto
import com.velogm.data.dto.response.PostListDto

interface SubscribeDataSource {
    suspend fun getTrendPost(): PostListDto

    suspend fun getFollowPost(): PostListDto

    suspend fun getFollower(): List<FollowerDto>
}