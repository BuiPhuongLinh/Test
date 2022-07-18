package com.cmc.demoweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun currentTemperatureType_Case01() {
        assert(viewModel.currentTemperatureType.value == TemperatureType.Celsius)
    }

    @Test
    fun currentTemperatureType_Case02() {
        viewModel.currentTemperatureType.value = TemperatureType.Fahrenheit
        assert(viewModel.currentTemperatureType.value == TemperatureType.Fahrenheit)
    }
}
