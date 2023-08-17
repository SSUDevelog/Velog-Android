package com.velogandroid.di

import com.data_remote.datasource.HomeDataSourceImpl
import com.data_remote.datasource.SignDataSourceImpl
import com.velogm.data.datasource.HomeDataSource
import com.velogm.data.datasource.SignDataSource
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
    abstract fun providesHomeDataSource(DataSourceImpl: HomeDataSourceImpl): HomeDataSource
}