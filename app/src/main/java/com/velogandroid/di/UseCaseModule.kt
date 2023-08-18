package com.velogandroid.di

import com.velogm.domain.repository.TagRepository
import com.velogm.domain.repository.SignRepository
import com.velogm.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideTokenUseCase(repository: SignRepository): AccessTokenUseCase {
        return AccessTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideHomeUseCase(repository: TagRepository): GetTagUseCase {
        return GetTagUseCase(repository)
    }

    @Singleton
    @Provides
    fun providePopularTagUseCase(repository: TagRepository): GetPopularTagUseCase {
        return GetPopularTagUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteTagUseCase(repository: TagRepository): DeleteTagUseCase {
        return DeleteTagUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddTagUseCase(repository: TagRepository): AddTagUseCase {
        return AddTagUseCase(repository)
    }
}