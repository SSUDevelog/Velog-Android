package com.velogm.domain.repository

import com.velogm.domain.model.PostList
import kotlinx.coroutines.flow.Flow

interface SubscribeRepository {

    suspend fun getTrendPosts(): Flow<PostList>
}