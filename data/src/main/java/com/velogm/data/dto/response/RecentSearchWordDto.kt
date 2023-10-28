package com.velogm.data.dto.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.velogm.domain.model.RecentSearchWord

@Entity(
    tableName = "recent_search_word"
)
data class RecentSearchWordDto(
    @ColumnInfo(name = "access_token") val accessToken: String,
    @PrimaryKey @ColumnInfo(name = "word") val recentSearchWord: String
) {
    fun toRecentSearchWordEntity() = RecentSearchWord(
        recentSearchWord
    )
}