package com.velogm.domain.usecase

import com.velogm.domain.model.Follower
import com.velogm.domain.repository.SubscribeRepository
import kotlinx.coroutines.flow.Flow

class GetFollowerUseCase(
    private val repository: SubscribeRepository
) {
    suspend operator fun invoke(): Flow<List<Follower>> = repository.getFollower()
}