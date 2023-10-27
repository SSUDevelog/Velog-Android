package com.velogm.data_local.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.velogm.data.dto.response.RecentSearchWordDto
import com.velogm.data_local.datasource.dao.RecentSearchWordDao

@Database(entities = [RecentSearchWordDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recentSearchWordDao(): RecentSearchWordDao
}