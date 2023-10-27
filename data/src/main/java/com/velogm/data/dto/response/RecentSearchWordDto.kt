package com.velogm.data.dto.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.velogm.domain.model.RecentSearchWord

@Entity(
    tableName = "recent_search_word"
)
data class RecentSearchWordDto(
    @PrimaryKey @ColumnInfo(name = "access_token") val accessToken: String,
    @ColumnInfo(name = "recent_search_word") val recentSearchWord: String
) {
    fun toRecentSearchWordEntity() = RecentSearchWord(
        accessToken
    )
}