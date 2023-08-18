package com.velogm.data.datasource

interface TagDataSource {
    suspend fun getTag(): List<String>
    suspend fun getPopularTag(): List<String>
    suspend fun deleteTag(tag:String): String
}