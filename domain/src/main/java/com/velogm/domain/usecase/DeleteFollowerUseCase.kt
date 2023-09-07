package com.velogm.domain.usecase

import com.velogm.domain.model.DeleteFollower
import com.velogm.domain.repository.SubscribeRepository
import kotlinx.coroutines.flow.Flow

class DeleteFollowerUseCase(
    private val repository: SubscribeRepository
) {
    suspend operator fun invoke(followerName: String): Flow<DeleteFollower> =
        repository.deleteFollower(followerName)
}