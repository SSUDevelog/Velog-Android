package com.velogm.domain.usecase

import com.velogm.domain.repository.SearchRepository

class AddRecentSearchWordUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(word: String) = repository.addRecentSearchWord(word)
}