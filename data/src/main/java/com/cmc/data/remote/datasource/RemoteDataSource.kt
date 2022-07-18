package com.cmc.data.remote.datasource

import com.cmc.domain.entities.ForecastWeather
import com.cmc.domain.entities.WeatherCurrent
import com.cmc.sharelocal.network.NetworkStatus

interface RemoteDataSource {

    suspend fun getTodayWeather(city: String): NetworkStatus<WeatherCurrent>
    suspend fun getTodayWeather(lat: Double, long: Double): NetworkStatus<WeatherCurrent>
    suspend fun getWeatherForecast(lat: String, long: String): NetworkStatus<ForecastWeather>
}