package com.template.data.repository

import com.template.data.coroutines.DispatcherProvider
import com.template.data.remote.datasource.RemoteDataSource
import com.template.domain.entities.ForecastWeather
import com.template.domain.entities.WeatherCurrent
import com.template.domain.repository.WeatherRepository
import com.template.sharelocal.network.NetworkStatus

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