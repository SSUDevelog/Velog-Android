package com.velogandroid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
//    @Singleton
//    @Provides
//    fun provideTicketUseCase(repository: TestRepository): TestUseCase {
//        return TestUseCase(repository)
//    }

}