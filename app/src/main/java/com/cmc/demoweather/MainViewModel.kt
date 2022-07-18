package com.cmc.demoweather

import androidx.lifecycle.MutableLiveData
import com.cmc.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    var currentTemperatureType = MutableLiveData(TemperatureType.Celsius)
}

enum class TemperatureType(val unit: String) {
    Celsius("C"),
    Fahrenheit("F");

    companion object {
        fun byPosition(position: Int): TemperatureType {
            return if (values().size > position) {
                values()[position]
            } else Celsius
        }
    }
}