package com.template.basekotlincleanarchitecture.weather

import androidx.lifecycle.viewModelScope
import com.template.common.base.BaseViewModel
import com.template.domain.entities.Coord
import com.template.domain.entities.WeatherCurrent
import com.template.domain.usecase.WeatherUseCase
import com.template.share.livedata.SingleLiveEvent
import com.template.sharelocal.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) :
    BaseViewModel() {
    val weatherCurrentEventLiveData: SingleLiveEvent<WeatherCurrent?> = SingleLiveEvent()

    fun searchCurrentWeather(city: String?) {
        loadingIndicator.setValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            weatherUseCase.invoke(city ?: "").collect {
                if (it is NetworkStatus.Success) {
                    updateWeather(it.data)
                } else {
                    showDialogError.postValue(it.errorMessage)
                }
                loadingIndicator.postValue(false)
            }
        }
    }

    private fun updateWeather(it: WeatherCurrent?) {
        if (it != null) {
            weatherCurrentEventLiveData.postValue(it)
        }
    }

    fun getLocation(lat: Double, long: Double) {
        loadingIndicator.setValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            weatherUseCase.invoke(lat, long).collect {
                if (it is NetworkStatus.Success) {
                    updateWeather(it.data)
                } else {
                    showDialogError.postValue(it.errorMessage)
                }
                loadingIndicator.postValue(false)
            }
        }

    }

}