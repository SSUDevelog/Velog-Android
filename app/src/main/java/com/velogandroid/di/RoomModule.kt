package com.velogandroid.di

import android.content.Context
import androidx.room.Room
import com.velogm.data_local.datasource.AppDatabase
import com.velogm.data_local.datasource.dao.RecentSearchWordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "velog_search.db").build()

    @Provides
    @Singleton
    fun providesRoomDao(appDatabase: AppDatabase): RecentSearchWordDao =
        appDatabase.recentSearchWordDao()

}