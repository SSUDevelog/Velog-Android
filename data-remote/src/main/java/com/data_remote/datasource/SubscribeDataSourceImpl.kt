package com.data_remote.datasource

import com.data_remote.api.SubscribeApiService
import com.velogm.data.datasource.SubscribeDataSource
import com.velogm.data.dto.response.DeleteFollowerDto
import com.velogm.data.dto.response.FollowerDto
import com.velogm.data.dto.response.InputFollowerDto
import com.velogm.data.dto.response.PostFollowListDto
import com.velogm.data.dto.response.PostListDto
import javax.inject.Inject

class SubscribeDataSourceImpl @Inject constructor(
    private val apiService: SubscribeApiService
) : SubscribeDataSource {
    override suspend fun getTrendPost(): PostListDto {
        return apiService.getTrendPost()
    }

    override suspend fun getFollowPost(): PostFollowListDto {
        return apiService.getFollowPost()
    }

    override suspend fun getFollower(): List<FollowerDto> {
        return apiService.getFollower()
    }

    override suspend fun deleteFollower(followerName: String): DeleteFollowerDto {
        return apiService.deleteFollower(followerName)
    }

    override suspend fun getInputFollower(followerName: String?): InputFollowerDto {
        return apiService.getInputFollower(followerName)
    }

    override suspend fun postAddFollower(followerName: String, fcmToken: String): String {
        return apiService.postAddFollower(fcmToken, followerName)
    }
}