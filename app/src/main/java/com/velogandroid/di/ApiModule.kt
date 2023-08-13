package com.velogandroid.di


import com.data_remote.api.SignApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
//    @Provides
//    @Singleton
//    fun provideBookMarkService(@VelogRetrofit retrofit: Retrofit): TestApiService =
//        retrofit.create(TestApiService::class.java)

    @Provides
    @Singleton
    fun provideSignService(@VelogRetrofit retrofit: Retrofit): SignApiService =
        retrofit.create(SignApiService::class.java)
}