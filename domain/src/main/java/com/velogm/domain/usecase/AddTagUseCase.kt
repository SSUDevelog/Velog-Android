package com.velogm.domain.usecase

import com.velogm.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow

class AddTagUseCase(
    private val repository: TagRepository
) {
    suspend operator fun invoke(tag:String): Flow<String> =
        repository.addTag(tag)
}
