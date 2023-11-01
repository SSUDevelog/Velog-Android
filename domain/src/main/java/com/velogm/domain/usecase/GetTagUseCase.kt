package com.velogm.domain.usecase

import com.velogm.domain.OutResult
import com.velogm.domain.model.Tag
import com.velogm.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow

class GetTagUseCase(
    private val repository: TagRepository
) {
    suspend operator fun invoke(): Flow<OutResult<List<Tag>>> =
        repository.getTag()
}