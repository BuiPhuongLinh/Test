package com.cmc.data.remote

import com.cmc.data.BuildConfig
import com.cmc.data.di.NetworkModule
import com.cmc.domain.entities.ForecastWeather
import com.cmc.domain.entities.WeatherCurrent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(NetworkModule.Service + "weather/")
    suspend fun getWeatherToday(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = BuildConfig.API_KEY
    ): Response<WeatherCurrent>

    @GET(NetworkModule.Service + "weather/")
    suspend fun getWeatherToday(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = BuildConfig.API_KEY
    ): Response<WeatherCurrent>

    @GET(NetworkModule.Service + "onecall")
    suspend fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "metric",
        @Query("exclude",encoded = true) exclude: String = "current,daily,alerts,minutely",
        @Query("appid") appid: String = BuildConfig.API_KEY
    ): Response<ForecastWeather>
}