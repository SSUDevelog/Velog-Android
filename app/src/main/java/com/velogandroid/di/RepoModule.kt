package com.velogandroid.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
//    @Singleton
//    @Binds
//    abstract fun providesBookMarkRepository(repoImpl: TestRepositoryImpl): TestRepository

}