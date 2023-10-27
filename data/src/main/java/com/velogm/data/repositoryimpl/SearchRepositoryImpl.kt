package com.velogm.data.repositoryimpl

import com.velogm.data.datasource.SearchDataSource
import com.velogm.domain.model.RecentSearchWord
import com.velogm.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override suspend fun getRecentSearchWord(): Flow<List<RecentSearchWord>> {
        return flow {
            val result = kotlin.runCatching {
                searchDataSource.getRecentSearchWord().map { it.toRecentSearchWordEntity() }
            }
            emit(result.getOrDefault(emptyList()))
        }
    }

    override suspend fun addRecentSearchWord(word: String): Flow<String> {
        return flow {
            val result = kotlin.runCatching {
                searchDataSource.addRecentSearchWord(word)
            }
            emit(result.getOrDefault("test").toString())
        }
    }
}