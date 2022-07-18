package com.cmc.data.repository

import com.cmc.data.coroutines.DispatcherProvider
import com.cmc.data.remote.datasource.RemoteDataSource
import com.cmc.domain.entities.ForecastWeather
import com.cmc.domain.entities.WeatherCurrent
import com.cmc.domain.repository.WeatherRepository
import com.cmc.sharelocal.network.NetworkStatus

class WeatherRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {

    override suspend fun getTodayWeather(city: String): NetworkStatus<WeatherCurrent> {
        return remoteDataSource.getTodayWeather(city)
    }

    override suspend fun getTodayWeather(lat: Double, long: Double): NetworkStatus<WeatherCurrent> {
        return remoteDataSource.getTodayWeather(lat, long)
    }

    override suspend fun getWeatherForecast(
        lat: String,
        long: String
    ): NetworkStatus<ForecastWeather> {
        return remoteDataSource.getWeatherForecast(lat, long)
    }
}