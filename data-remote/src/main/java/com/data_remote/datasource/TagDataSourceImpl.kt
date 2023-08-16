package com.data_remote.datasource

import com.data_remote.api.TagApiService
import com.velogm.data.datasource.TagDataSource
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

}