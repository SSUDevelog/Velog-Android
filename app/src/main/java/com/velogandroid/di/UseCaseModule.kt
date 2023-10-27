package com.velogandroid.di

import com.velogm.domain.repository.SearchRepository
import com.velogm.domain.repository.SignRepository
import com.velogm.domain.repository.SubscribeRepository
import com.velogm.domain.repository.TagRepository
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

    @Singleton
    @Provides
    fun provideGetTrendPostUseCase(repository: SubscribeRepository): GetTrendPostUseCase {
        return GetTrendPostUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetFollowPostUseCase(repository: SubscribeRepository): GetFollowPostsUseCase {
        return GetFollowPostsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetTagPostUseCase(repository: TagRepository): GetTagPostsUseCase {
        return GetTagPostsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetFollowerUseCase(repository: SubscribeRepository): GetFollowerUseCase {
        return GetFollowerUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteFollowerUseCase(repository: SubscribeRepository): DeleteFollowerUseCase {
        return DeleteFollowerUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetInputFollowerUseCase(repository: SubscribeRepository): GetInputFollowerUseCase {
        return GetInputFollowerUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddFollowerUseCase(repository: SubscribeRepository): AddFollowerUseCase {
        return AddFollowerUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetRecentSearchWordUseCase(repository: SearchRepository): GetRecentSearchWordUseCase {
        return GetRecentSearchWordUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAddRecentWordUseCase(repository: SearchRepository): AddRecentSearchWordUseCase {
        return AddRecentSearchWordUseCase(repository)
    }
}