package com.velogandroid.di

import com.velogm.data.repositoryimpl.AuthRepositoryImpl
import com.velogm.data.repositoryimpl.SearchRepositoryImpl
import com.velogm.data.repositoryimpl.SignRepositoryImpl
import com.velogm.data.repositoryimpl.SubscribeRepositoryImpl
import com.velogm.data.repositoryimpl.TagRepositoryImpl
import com.velogm.domain.repository.AuthRepository
import com.velogm.domain.repository.SearchRepository
import com.velogm.domain.repository.SignRepository
import com.velogm.domain.repository.SubscribeRepository
import com.velogm.domain.repository.TagRepository
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
        RepositoryImpl: TagRepositoryImpl,
    ): TagRepository

    @Binds
    @Singleton
    abstract fun bindsSubscribeRepository(
        RepositoryImpl: SubscribeRepositoryImpl,
    ): SubscribeRepository

    @Binds
    @Singleton
    abstract fun bindsSearchRepository(
        RepositoryImpl: SearchRepositoryImpl,
    ): SearchRepository
}