package com.velogandroid.di

import com.velogm.data.repositoryimpl.AuthRepositoryImpl
import com.velogm.data.repositoryimpl.HomeRepositoryImpl
import com.velogm.data.repositoryimpl.SignRepositoryImpl
import com.velogm.domain.repository.AuthRepository
import com.velogm.domain.repository.HomeRepository
import com.velogm.domain.repository.SignRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAuthRepository(
        RepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsSignRepository(
        RepositoryImpl: SignRepositoryImpl,
    ): SignRepository

    @Binds
    @Singleton
    abstract fun bindsHomeRepository(
        RepositoryImpl: HomeRepositoryImpl,
    ): HomeRepository

}