package com.data_remote.datasource

import com.data_remote.api.TagApiService
import com.velogm.data.datasource.TagDataSource
import com.velogm.data.dto.response.PostListDto
import com.velogm.data.dto.response.TrendPostDto
import javax.inject.Inject

class TagDataSourceImpl @Inject constructor(
    private val apiService: TagApiService
) : TagDataSource {


    override suspend fun getTag(): List<String> {
        return apiService.getTag()
    }

    override suspend fun getPopularTag(): List<String> {
        return apiService.getPopularTag()
    }

    override suspend fun deleteTag(tag: String): String {
        return apiService.deleteTag(tag)
    }

    override suspend fun addTag(tag: String): String {
        return apiService.addTag(tag)
    }

    override suspend fun getTagPosts(tag: String): List<TrendPostDto> {
        return apiService.getTagPost(tag)
    }

}