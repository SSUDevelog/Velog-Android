package com.velogm.data.datasource

import com.velogm.data.dto.response.PostListDto
import com.velogm.data.dto.response.TrendPostDto

interface TagDataSource {
    suspend fun getTag(): List<String>
    suspend fun getPopularTag(): List<String>
    suspend fun deleteTag(tag:String): String
    suspend fun addTag(tag:String): String

    suspend fun getTagPosts(tag:String) : List<TrendPostDto>
}