package com.velogm.domain.usecase

import com.velogm.domain.model.PostList
import com.velogm.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow

class GetTagPostsUseCase(
    private val repository: TagRepository
) {
    suspend operator fun invoke(tag:String): Flow<PostList> = repository.getTagPosts(tag)
}