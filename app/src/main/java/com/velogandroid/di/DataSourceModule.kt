package com.velogandroid.di

import com.velogm.data.datasource.SignDataSource
import com.data_remote.datasource.SignDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

//    @Singleton
//    @Binds
//    abstract fun providesBookMarkDataSource(DataSourceImpl: TestDataSourceImpl): TestDataSource

    @Singleton
    @Binds
    abstract fun providesSignDataSource(DataSourceImpl: SignDataSourceImpl): SignDataSource
}