package com.template.sharelocal

import kotlin.math.roundToInt

fun Float.convertCelsiusToFahrenheit(): Int {
    return (this * 9.0f / 5.0f + 32).roundToInt()
}

fun Float.convertFahrenheitToCelsius(): Int {
    return ((this - 32) * 5 / 9).roundToInt()
}