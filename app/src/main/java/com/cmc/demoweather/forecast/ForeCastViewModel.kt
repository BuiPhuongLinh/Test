package com.cmc.demoweather.forecast

import androidx.lifecycle.viewModelScope
import com.cmc.common.base.BaseViewModel
import com.cmc.domain.entities.ForecastWeather
import com.cmc.domain.usecase.WeatherUseCase
import com.cmc.sharelocal.livedata.SingleLiveEvent
import com.cmc.sharelocal.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForeCastViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) :
    BaseViewModel() {

    var listForecastWeatherLiveData: SingleLiveEvent<ForecastWeather> = SingleLiveEvent()

    fun getForeCastWeather(lat: String, long: String) {
        loadingIndicator.setValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            weatherUseCase.invoke(lat, long).collect {
                if (it is NetworkStatus.Success) {
                    listForecastWeatherLiveData.postValue(it.data!!)
                } else {
                    showDialogError.postValue(it.errorMessage)
                    clickBackEvent.postValue(null)
                }
                loadingIndicator.postValue(false)
            }
        }
    }
}