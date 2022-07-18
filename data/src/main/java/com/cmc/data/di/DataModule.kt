package com.cmc.data.di

import com.cmc.data.coroutines.DefaultDispatcherProvider
import com.cmc.data.coroutines.DispatcherProvider
import com.cmc.data.remote.ApiService
import com.cmc.data.remote.datasource.RemoteDataSource
import com.cmc.data.remote.datasource.RemoteDataSourceImpl
import com.cmc.data.repository.WeatherRepositoryImpl
import com.cmc.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    internal fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCompetitionsRepository(
        dispatcher: DispatcherProvider,
        remoteDataSource: RemoteDataSource
    ): WeatherRepository {
        return WeatherRepositoryImpl(dispatcher, remoteDataSource)
    }
}