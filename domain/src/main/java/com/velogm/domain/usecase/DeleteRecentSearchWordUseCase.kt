package com.velogm.domain.usecase

import com.velogm.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class DeleteRecentSearchWordUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(): Flow<String> = repository.deleteRecentSearchWord()
}