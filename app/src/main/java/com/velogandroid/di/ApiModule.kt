package com.velogandroid.di


import com.data_remote.api.TagApiService
import com.data_remote.api.SignApiService
import com.data_remote.api.SubscribeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideSignService(@VelogRetrofit retrofit: Retrofit): SignApiService =
        retrofit.create(SignApiService::class.java)

    @Provides
    @Singleton
    fun provideHomeService(@VelogRetrofit retrofit: Retrofit): TagApiService =
        retrofit.create(TagApiService::class.java)

    @Provides
    @Singleton
    fun provideSubscribeService(@VelogRetrofit retrofit: Retrofit): SubscribeApiService =
        retrofit.create(SubscribeApiService::class.java)
}