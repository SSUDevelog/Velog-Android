package com.velogm.domain.repository

import com.velogm.domain.model.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    suspend fun getTag(): Flow<List<Tag>>
    suspend fun getPopularTag():Flow<List<Tag>>
    suspend fun deleteTag(tag:String):Flow<String>
}