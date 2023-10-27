package com.velogandroid.di

import com.data_remote.datasource.SignDataSourceImpl
import com.data_remote.datasource.SubscribeDataSourceImpl
import com.data_remote.datasource.TagDataSourceImpl
import com.velogm.data.datasource.SearchDataSource
import com.velogm.data.datasource.SharedPreferencesDataSource
import com.velogm.data.datasource.SignDataSource
import com.velogm.data.datasource.SubscribeDataSource
import com.velogm.data.datasource.TagDataSource
import com.velogm.data_local.datasource.SearchDataSourceImpl
import com.velogm.data_local.datasource.SharedPreferencesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun providesSignDataSource(DataSourceImpl: SignDataSourceImpl): SignDataSource

    @Singleton
    @Binds
    abstract fun providesHomeDataSource(DataSourceImpl: TagDataSourceImpl): TagDataSource

    @Singleton
    @Binds
    abstract fun providesSubscribeDataSource(DataSourceImpl: SubscribeDataSourceImpl): SubscribeDataSource

    @Singleton
    @Binds
    abstract fun providesSearchDataSource(DataSourceImpl: SearchDataSourceImpl): SearchDataSource

    @Singleton
    @Binds
    abstract fun providesSharedPreferencesDataSource(DataSourceImpl: SharedPreferencesDataSourceImpl): SharedPreferencesDataSource
}