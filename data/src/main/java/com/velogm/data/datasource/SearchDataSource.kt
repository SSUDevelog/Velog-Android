package com.velogm.data.datasource

import com.velogm.data.dto.response.RecentSearchWordDto

interface SearchDataSource {
    suspend fun getRecentSearchWord(): List<RecentSearchWordDto>
    suspend fun addRecentSearchWord(word: String)
}