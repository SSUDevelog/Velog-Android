package com.data_remote.datasource

import com.data_remote.api.HomeApiService
import com.velogm.data.datasource.HomeDataSource
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val apiService: HomeApiService
) : HomeDataSource {


    override suspend fun getTag(): List<String> {
        return apiService.getTag()
    }

}