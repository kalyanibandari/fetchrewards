package com.kalyani.fetchrewards.di

import com.kalyani.fetchrewards.network.ApiService
import com.kalyani.fetchrewards.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(retrofitInstance: RetrofitInstance) : ApiService {
        return retrofitInstance.buildApi(ApiService::class.java)
    }

}