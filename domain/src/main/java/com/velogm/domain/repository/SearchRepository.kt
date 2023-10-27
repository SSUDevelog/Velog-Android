package com.velogm.domain.repository

import com.velogm.domain.model.RecentSearchWord
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getRecentSearchWord(): Flow<List<RecentSearchWord>>
    suspend fun addRecentSearchWord(word: String): Flow<String>
}