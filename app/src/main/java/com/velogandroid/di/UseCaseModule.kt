package com.velogandroid.di

import com.velogm.domain.repository.SignRepository
import com.velogm.domain.usecase.AccessTokenUseCase
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
}