package com.velogm.data_local.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.velogm.data.dto.response.RecentSearchWordDto

@Dao
interface RecentSearchWordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recentSearchWordDto: RecentSearchWordDto)

    @Query("SELECT * FROM recent_search_word Where access_token = (:token)")
    suspend fun load(token: String): List<RecentSearchWordDto>

    @Query("DELETE FROM recent_search_word WHERE access_token = :token")
    suspend fun deleteByToken(token: String)
}