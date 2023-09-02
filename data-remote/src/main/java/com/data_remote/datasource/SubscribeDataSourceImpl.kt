package com.data_remote.datasource

import com.data_remote.api.SubscribeApiService
import com.velogm.data.datasource.SubscribeDataSource
import com.velogm.data.dto.response.FollowerDto
import com.velogm.data.dto.response.PostListDto
import javax.inject.Inject

class SubscribeDataSourceImpl @Inject constructor(
    private val apiService: SubscribeApiService
) : SubscribeDataSource {
    override suspend fun getTrendPost(): PostListDto {
        return apiService.getTrendPost()
    }

    override suspend fun getFollowPost(): PostListDto {
        return apiService.getFollowPost()
    }

    override suspend fun getFollower(): List<FollowerDto> {
        return apiService.getFollower()
    }

}