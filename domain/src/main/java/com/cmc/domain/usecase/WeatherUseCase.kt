package com.cmc.domain.usecase

import com.cmc.domain.entities.ForecastWeather
import com.cmc.domain.entities.WeatherCurrent
import com.cmc.domain.repository.WeatherRepository
import com.cmc.sharelocal.network.NetworkStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(city: String): Flow<NetworkStatus<WeatherCurrent>> {
        return flow {
            emit(weatherRepository.getTodayWeather(city))
        }
    }

    suspend operator fun invoke(lat: Double, long: Double): Flow<NetworkStatus<WeatherCurrent>> {
        return flow {
            emit(weatherRepository.getTodayWeather(lat, long))
        }

    }

    suspend operator fun invoke(
        lat: String,
        long: String
    ): Flow<NetworkStatus<ForecastWeather>> {
        return flow {
            emit(weatherRepository.getWeatherForecast(lat, long))
        }

    }

}

