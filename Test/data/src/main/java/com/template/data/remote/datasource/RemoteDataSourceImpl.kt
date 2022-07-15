package com.template.data.remote.datasource

import com.template.data.remote.ApiService
import com.template.data.remote.safeApiCall
import com.template.domain.entities.ForecastWeather
import com.template.domain.entities.WeatherCurrent
import com.template.sharelocal.network.NetworkStatus

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getTodayWeather(city: String): NetworkStatus<WeatherCurrent> {
        return safeApiCall { apiService.getWeatherToday(city = city) }
    }

    override suspend fun getTodayWeather(lat: Double, long: Double): NetworkStatus<WeatherCurrent> {
        return safeApiCall { apiService.getWeatherToday(lat, long) }
    }

    override suspend fun getWeatherForecast(
        lat: String,
        long: String
    ): NetworkStatus<ForecastWeather> {
        return safeApiCall { apiService.getWeatherForecast(lat, long) }
    }

}