package com.velogm.domain.usecase

import com.velogm.domain.repository.SubscribeRepository
import kotlinx.coroutines.flow.Flow

class AddFollowerUseCase(
    private val repository: SubscribeRepository
) {
    suspend operator fun invoke(followerName: String): Flow<String> =
        repository.postAddFollower(followerName)
}