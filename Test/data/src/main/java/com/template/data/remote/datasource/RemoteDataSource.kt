package com.template.data.remote.datasource

import com.template.domain.entities.ForecastWeather
import com.template.domain.entities.WeatherCurrent
import com.template.sharelocal.network.NetworkStatus

interface RemoteDataSource {

    suspend fun getTodayWeather(city: String): NetworkStatus<WeatherCurrent>
    suspend fun getTodayWeather(lat: Double, long: Double): NetworkStatus<WeatherCurrent>
    suspend fun getWeatherForecast(lat: String, long: String): NetworkStatus<ForecastWeather>
}