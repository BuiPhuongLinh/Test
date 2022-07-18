package com.cmc.domain.entities

import android.os.Parcelable
import androidx.databinding.BaseObservable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastWeather(
    val timezone: String? = "",
    val lon: String? = "",
    val lat: String? = "",
    val hourly: MutableList<Hourly>? = mutableListOf()
) : BaseObservable(), Parcelable

@Parcelize
data class Hourly(
    val dt: Long? = 0,
    val temp: Double? = 0.0,
    val humidity: Int? = 0,
) : BaseObservable(), Parcelable {
    var temperatureType : Int? = 0
}
