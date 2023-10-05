package com.velogm.domain.usecase

import com.velogm.domain.model.InputFollower
import com.velogm.domain.repository.SubscribeRepository
import kotlinx.coroutines.flow.Flow

class GetInputFollowerUseCase(
    private val repository: SubscribeRepository
) {
    suspend operator fun invoke(followerName: String?): Flow<InputFollower> =
        repository.getInputFollower(followerName)
}