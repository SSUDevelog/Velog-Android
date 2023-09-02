package com.velogm.data.repositoryimpl

import com.velogm.data.datasource.SubscribeDataSource
import com.velogm.domain.model.Follower
import com.velogm.domain.model.PostList
import com.velogm.domain.repository.SubscribeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubscribeRepositoryImpl @Inject constructor(
    private val dataSource: SubscribeDataSource
) : SubscribeRepository {

    override suspend fun getTrendPosts(): Flow<PostList> {
        return flow {
            val result = runCatching {
                dataSource.getTrendPost().toPostListEntity()
            }
            emit(result.getOrDefault(PostList(emptyList())))
        }
    }

    override suspend fun getFollowPosts(): Flow<PostList> {
        return flow {
            val result = runCatching {
                dataSource.getFollowPost().toPostListEntity()
            }
            emit(result.getOrDefault(PostList(emptyList())))
        }
    }

    override suspend fun getFollower(): Flow<List<Follower>> {
        return flow {
            val result = runCatching {
                dataSource.getFollower().map { followerDto -> followerDto.toFollowerEntity() }
            }
            emit(result.getOrDefault(emptyList()))
        }
    }

}