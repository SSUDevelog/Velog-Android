package com.velogm.data.datasource

import com.velogm.data.dto.response.PostListDto

interface SubscribeDataSource {
    suspend fun getTrendPost(): PostListDto
}