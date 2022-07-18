package com.cmc.sharelocal.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorBody(val cod: String, val message: String)