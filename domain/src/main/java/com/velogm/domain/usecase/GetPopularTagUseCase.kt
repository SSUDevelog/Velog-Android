package com.velogm.domain.usecase

import com.velogm.domain.model.Tag
import com.velogm.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow

class GetPopularTagUseCase(
    private val repository: TagRepository
) {
    suspend operator fun invoke(): Flow<List<Tag>> =
        repository.getPopularTag()
}