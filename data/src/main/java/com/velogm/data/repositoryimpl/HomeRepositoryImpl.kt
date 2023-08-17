package com.velogm.data.repositoryimpl

import com.velogm.data.datasource.HomeDataSource
import com.velogm.domain.model.Tag
import com.velogm.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override suspend fun getTag(): Flow<List<Tag>> {
        return flow {
            val result = kotlin.runCatching {
                dataSource.getTag().map { Tag(it) }
            }
            emit(result.getOrDefault(emptyList()))
        }
    }
}