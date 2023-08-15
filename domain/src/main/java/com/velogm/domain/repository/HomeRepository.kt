package com.velogm.domain.repository

import com.velogm.domain.model.Tag
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getTag(): Flow<List<Tag>>
}