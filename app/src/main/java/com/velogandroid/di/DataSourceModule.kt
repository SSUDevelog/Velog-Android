package com.velogandroid.di

import com.data_remote.datasource.TagDataSourceImpl
import com.data_remote.datasource.SignDataSourceImpl
import com.data_remote.datasource.SubscribeDataSourceImpl
import com.velogm.data.datasource.TagDataSource
import com.velogm.data.datasource.SignDataSource
import com.velogm.data.datasource.SubscribeDataSource
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

}