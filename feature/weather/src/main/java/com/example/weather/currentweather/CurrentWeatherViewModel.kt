package com.example.weather.currentweather

import androidx.lifecycle.viewModelScope
import com.template.common.base.BaseViewModel
import com.template.domain.entities.WeatherCurrent
import com.template.domain.usecase.WeatherUseCase
import com.template.sharelocal.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : BaseViewModel() {
    fun searchCurrentWeather(city: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherUseCase.invoke(city ?: "Hanoi").collect {
                if (it is NetworkStatus.Success) {
                    updateWeather(it.data)
                }
            }
        }
    }

    private fun updateWeather(it: WeatherCurrent?) {

    }
}