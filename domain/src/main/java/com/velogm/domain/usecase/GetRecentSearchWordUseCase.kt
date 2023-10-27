package com.velogm.domain.usecase

import com.velogm.domain.model.RecentSearchWord
import com.velogm.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class GetRecentSearchWordUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(): Flow<List<RecentSearchWord>> =
        repository.getRecentSearchWord()
}