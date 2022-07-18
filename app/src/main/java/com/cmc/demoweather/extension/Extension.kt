package com.cmc.demoweather.extension

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.cmc.demoweather.TemperatureType
import com.cmc.sharelocal.convertCelsiusToFahrenheit
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


fun Float.getTemperatureByType(temperatureType: TemperatureType? = TemperatureType.Celsius): String {
    return if (temperatureType == TemperatureType.Celsius) {
        "${this.toInt()} ${temperatureType.unit}"
    } else {
        "${this.convertCelsiusToFahrenheit()} ${temperatureType?.unit}"
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    isRemoveAfterFirstOne: Boolean = true,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            if (isRemoveAfterFirstOne) {
                this@getOrAwaitValue.removeObserver(this)
            }
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}