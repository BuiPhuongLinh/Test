package com.template.basekotlincleanarchitecture.forecast

import androidx.lifecycle.viewModelScope
import com.template.common.base.BaseViewModel
import com.template.domain.entities.ForecastWeather
import com.template.domain.usecase.WeatherUseCase
import com.template.share.livedata.SingleLiveEvent
import com.template.sharelocal.network.NetworkStatus
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