package com.velogm.domain.usecase

import com.velogm.domain.model.Tag
import com.velogm.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetTagUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Flow<List<Tag>> =
        repository.getTag()
}