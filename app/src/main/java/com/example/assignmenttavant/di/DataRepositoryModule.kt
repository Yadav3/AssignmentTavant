package com.example.assignmenttavant.di

import com.example.assignmenttavant.network.ApiService
import com.example.assignmenttavant.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

object DataRepositoryModule {

    @Provides
    fun provideDataRepository(apiService: ApiService): DataRepository {
        return DataRepository(apiService)
    }
}