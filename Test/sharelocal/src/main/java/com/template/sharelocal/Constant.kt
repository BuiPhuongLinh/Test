package com.template.sharelocal

enum class Constant {
    KEY_FORECAST("KEY_FORECAST"),
    KEY_FORECAST_LAT("KEY_FORECAST_LAT"),
    KEY_FORECAST_LON("KEY_FORECAST"),
    ;

    var value: String

    constructor(value: String) {
        this.value = value
    }
}