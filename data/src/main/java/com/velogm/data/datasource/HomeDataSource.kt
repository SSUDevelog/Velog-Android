package com.velogm.data.datasource

interface HomeDataSource {
    suspend fun getTag(): List<String>
}