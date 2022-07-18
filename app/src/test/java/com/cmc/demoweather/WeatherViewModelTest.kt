package com.cmc.demoweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cmc.demoweather.extension.getOrAwaitValue
import com.cmc.demoweather.weather.WeatherViewModel
import com.cmc.domain.entities.WeatherCurrent
import com.cmc.domain.usecase.WeatherUseCase
import com.cmc.sharelocal.network.NetworkStatus
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: WeatherViewModel

    private val weatherUseCase = mockk<WeatherUseCase>()

    @Before
    fun setup() {
        viewModel = WeatherViewModel(
            weatherUseCase
        )
    }

    @Test
    fun getLatLong_Case01() {
        val lat = 100.5
        val long = 100.5
        val response = WeatherCurrent(
            name = "res"
        )

        coEvery {
            weatherUseCase.invoke(lat, long)
        } returns flow {
            emit(NetworkStatus.Success(response))
        }
        viewModel.getLocation(lat, long)
        assert(viewModel.weatherCurrentEventLiveData.getOrAwaitValue()?.name == response.name)
    }

    @Test
    fun getLatLong_Case02() {
        val lat = 100.5
        val long = 100.5

        coEvery {
            weatherUseCase.invoke(lat, long)
        } returns flow {
            emit(NetworkStatus.Success<WeatherCurrent>(null))
        }
        viewModel.getLocation(lat, long)
        assert(viewModel.weatherCurrentEventLiveData.value == null)
    }

    @Test
    fun getLatLong_Case03() {
        val lat = 100.5
        val long = 100.5
        val errorMess = "error"

        coEvery {
            weatherUseCase.invoke(lat, long)
        } returns flow {
            emit(NetworkStatus.Error<WeatherCurrent>(errorMess))
        }
        viewModel.getLocation(lat, long)
        assert(viewModel.weatherCurrentEventLiveData.value == null)
    }

    @Test
    fun searchWeather_Case01() {
        val query = "Hanoi"
        val response = WeatherCurrent(
            name = "resHaNoi"
        )
        coEvery {
            weatherUseCase.invoke(query)
        } returns flow {
            emit(NetworkStatus.Success(response))
        }
        viewModel.searchCurrentWeather(query)
        assert(viewModel.weatherCurrentEventLiveData.getOrAwaitValue()?.name == response.name)
    }

    @Test
    fun searchWeather_Case02() {
        val query = "Hanoi"
        coEvery {
            weatherUseCase.invoke(query)
        } returns flow {
            emit(NetworkStatus.Success<WeatherCurrent>(null))
        }
        viewModel.searchCurrentWeather(query)
        assert(viewModel.weatherCurrentEventLiveData.value == null)
    }

    @Test
    fun searchWeather_Case03() {
        val query = "Hanoi"
        val errorMess = "error"

        coEvery {
            weatherUseCase.invoke(query)
        } returns flow {
            emit(NetworkStatus.Error<WeatherCurrent>(errorMess))
        }
        viewModel.searchCurrentWeather(query)
        assert(viewModel.weatherCurrentEventLiveData.value == null)
    }
}