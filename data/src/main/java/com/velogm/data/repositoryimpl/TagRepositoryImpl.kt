package com.velogm.data.repositoryimpl

import com.velogm.data.datasource.TagDataSource
import com.velogm.domain.model.Tag
import com.velogm.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val dataSource: TagDataSource
) : TagRepository {

    override suspend fun getTag(): Flow<List<Tag>> {
        return flow {
            val result = kotlin.runCatching {
                dataSource.getTag().map { Tag(it) }
            }
            emit(result.getOrDefault(emptyList()))
        }
    }

    override suspend fun getPopularTag(): Flow<List<Tag>> {
        return flow {
            val result = kotlin.runCatching {
                dataSource.getPopularTag().map { Tag(it) }
            }
            emit(result.getOrDefault(emptyList()))
        }
    }
}