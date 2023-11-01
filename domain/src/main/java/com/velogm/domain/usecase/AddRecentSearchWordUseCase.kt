package com.velogm.domain.usecase

import com.velogm.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class AddRecentSearchWordUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(word: String): Flow<String> = repository.addRecentSearchWord(word)
}