package com.velogm.data.repositoryimpl

import com.velogm.data.datasource.TagDataSource
import com.velogm.domain.OutResult
import com.velogm.domain.model.PostList
import com.velogm.domain.model.Tag
import com.velogm.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.RuntimeException
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val dataSource: TagDataSource
) : TagRepository {

    override suspend fun getTag(): Flow<OutResult<List<Tag>>> = flow {
        val result = runCatching {
            val tag = dataSource.getTag().map { Tag(it) }
            OutResult.Success(tag)
        }
        val outcome = result.getOrElse {
            val errorCode = (it as? HttpException)?.code() ?: -1
            OutResult.Failure(error = VelogHttpException(errorCode, "$errorCode"))
        }
        emit(outcome)
    }

    override suspend fun getPopularTag(): Flow<List<Tag>> {
        return flow {
            val result = kotlin.runCatching {
                dataSource.getPopularTag().map { Tag(it) }
            }
            emit(result.getOrDefault(emptyList()))
        }
    }

    override suspend fun deleteTag(tag:String): Flow<String> {
        return flow {
            val result = kotlin.runCatching {
                dataSource.deleteTag(tag)
            }
            emit(result.getOrDefault("test"))
        }
    }

    override suspend fun addTag(tag: String): Flow<String> {
        return flow {
            val result = kotlin.runCatching {
                dataSource.addTag(tag)
            }
            emit(result.getOrDefault("test"))
        }
    }

    override suspend fun getTagPosts(tag: String): Flow<PostList> {
        return flow {
            val result = runCatching {
                PostList(dataSource.getTagPosts(tag).map { it.toPostEntity() })
            }
            emit(result.getOrDefault(PostList(emptyList())))
        }
    }
}

class VelogHttpException(
    val httpCode: Int,
    override val message: String,
) : RuntimeException()