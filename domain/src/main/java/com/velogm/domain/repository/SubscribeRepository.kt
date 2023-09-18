package com.velogm.domain.repository

import com.velogm.domain.model.DeleteFollower
import com.velogm.domain.model.Follower
import com.velogm.domain.model.InputFollower
import com.velogm.domain.model.PostList
import kotlinx.coroutines.flow.Flow

interface SubscribeRepository {
    suspend fun getTrendPosts(): Flow<PostList>

    suspend fun getFollowPosts(): Flow<PostList>

    suspend fun getFollower(): Flow<List<Follower>>

    suspend fun deleteFollower(followerName: String): Flow<DeleteFollower>

    suspend fun getInputFollower(followerName: String?): Flow<InputFollower>

    suspend fun postAddFollower(followerName: String): Flow<String>
}