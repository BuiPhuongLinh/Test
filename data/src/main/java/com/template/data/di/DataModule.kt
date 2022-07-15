package com.template.data.di

import com.template.data.coroutines.DefaultDispatcherProvider
import com.template.data.coroutines.DispatcherProvider
import com.template.data.remote.ApiService
import com.template.data.remote.datasource.RemoteDataSource
import com.template.data.remote.datasource.RemoteDataSourceImpl
import com.template.data.repository.WeatherRepositoryImpl
import com.template.domain.repository.WeatherRepository
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