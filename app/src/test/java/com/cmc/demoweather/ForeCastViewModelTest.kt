package com.cmc.demoweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cmc.demoweather.extension.getOrAwaitValue
import com.cmc.demoweather.forecast.ForeCastViewModel
import com.cmc.domain.entities.ForecastWeather
import com.cmc.domain.usecase.WeatherUseCase
import com.cmc.sharelocal.network.NetworkStatus
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForeCastViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ForeCastViewModel

    private val weatherUseCase = mockk<WeatherUseCase>()

    @Before
    fun setup() {
        viewModel = ForeCastViewModel(
            weatherUseCase
        )
    }

    @Test
    fun getForeCaseWeather_Case01() {
        val lat = 100.5.toString()
        val long = 100.5.toString()
        val response = ForecastWeather(
            lat = lat,
            lon = long
        )

        coEvery {
            weatherUseCase.invoke(lat, long)
        } returns flow {
            emit(NetworkStatus.Success(response))
        }
        viewModel.getForeCastWeather(lat, long)
        assert(viewModel.listForecastWeatherLiveData.getOrAwaitValue()?.lat == response.lat)
    }

    @Test
    fun getLatLong_Case03() {
        val lat = 100.5.toString()
        val long = 100.5.toString()

        val errorMess = "error"

        coEvery {
            weatherUseCase.invoke(lat, long)
        } returns flow {
            emit(NetworkStatus.Error<ForecastWeather>(errorMess))
        }
        viewModel.getForeCastWeather(lat, long)
        assert(viewModel.listForecastWeatherLiveData.value == null)
    }
}
