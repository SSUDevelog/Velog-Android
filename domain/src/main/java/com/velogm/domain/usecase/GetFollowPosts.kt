package com.velogm.domain.usecase

import com.velogm.domain.model.PostList
import com.velogm.domain.repository.SubscribeRepository
import kotlinx.coroutines.flow.Flow

class GetFollowPosts(
    private val repository: SubscribeRepository
) {
    suspend operator fun invoke(): Flow<PostList> = repository.getFollowPosts()
}