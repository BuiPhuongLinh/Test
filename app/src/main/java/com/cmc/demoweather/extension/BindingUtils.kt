package com.cmc.demoweather.extension

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.cmc.demoweather.R
import com.cmc.demoweather.TemperatureType
import com.cmc.domain.entities.Hourly
import com.cmc.sharelocal.utcToLocal

object BindingUtils {

    @JvmStatic
    @BindingAdapter("textHumidity")
    fun setTextHumidity(textView: TextView, hourly: Hourly) {
        textView.text = String.format(
            format = textView.rootView.context.getString(R.string.text_humidity_percent_pattern),
            hourly.humidity.toString()
        )
    }

    @JvmStatic
    @BindingAdapter("textTemperature")
    fun setTextTemperature(textView: TextView, hourly: Hourly) {
        textView.text = hourly.temp?.toFloat()
            ?.getTemperatureByType(TemperatureType.byPosition(hourly.temperatureType ?: 0))
    }

    @JvmStatic
    @BindingAdapter("textTime")
    fun setTextTime(textView: TextView, hourly: Hourly) {
        textView.text = hourly.dt?.utcToLocal()
    }
}