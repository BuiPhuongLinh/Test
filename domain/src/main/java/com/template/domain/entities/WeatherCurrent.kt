package com.template.domain.entities

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.versionedparcelable.ParcelField
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherCurrent(
    val coord: Coord? = Coord(),
    val weather: List<Weather>? = mutableListOf(),
    val main: Main? = Main(),
    val name: String? = ""
) : BaseObservable(), Parcelable

@Parcelize
data class Coord(
    val lon: String? = "",
    val lat: String? = ""
) : BaseObservable(), Parcelable

@Parcelize
data class Weather(
    val id: Int? = 0,
    val main: String? = "",
    val description: String? = "",
    val icon: String? = ""
) : BaseObservable(), Parcelable

@Parcelize
data class Main(
    val temp: Float? = 0.0F,
    val feels_like: Float? = 0.0F,
    val temp_min: Float? = 0.0F,
    val temp_max: Float? = 0.0F,
    val humidity: Float? = 0.0F
) : BaseObservable(), Parcelable

