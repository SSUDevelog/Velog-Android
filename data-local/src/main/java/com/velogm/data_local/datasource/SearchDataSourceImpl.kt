package com.velogm.data_local.datasource

import android.content.SharedPreferences
import com.velogm.data.datasource.SearchDataSource
import com.velogm.data.dto.response.RecentSearchWordDto
import com.velogm.data_local.datasource.dao.RecentSearchWordDao
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val dao: RecentSearchWordDao, private val prefs: SharedPreferences
) : SearchDataSource {
    override suspend fun getRecentSearchWord(): List<RecentSearchWordDto> {
        return dao.load(prefs.getString("AccessToken", "") ?: "")
    }

    override suspend fun addRecentSearchWord(word: String) {
        dao.insert(
            RecentSearchWordDto(
                prefs.getString("AccessToken", "") ?: "", recentSearchWord = word
            )
        )
    }
}