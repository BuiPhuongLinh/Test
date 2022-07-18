package com.cmc.data.remote

import com.squareup.moshi.Moshi
import com.cmc.sharelocal.network.ErrorBody
import com.cmc.sharelocal.network.NetworkStatus
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


// Network Messages
const val SOCKET_TIME_OUT_EXCEPTION =
    "Request timed out while trying to connect. Please ensure you have a strong signal and try again."
const val UNKNOWN_NETWORK_EXCEPTION =
    "An unexpected error has occurred. Please check your network connection and try again."
const val CONNECT_EXCEPTION =
    "Could not connect to the server. Please check your internet connection and try again."
const val UNKNOWN_HOST_EXCEPTION =
    "Couldn't connect to the server at the moment. Please try again in a few minutes."

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): NetworkStatus<T> {
    try {
        val response = call.invoke()
        if (response.isSuccessful && response.body() != null) {
            return NetworkStatus.Success(response.body())
        }
        val messageError = response.errorBody()?.let {
            val errorJson = it.string()
            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(ErrorBody::class.java)
            jsonAdapter.fromJson(errorJson)?.message ?: response.message()
        } ?: response.message()
        return NetworkStatus.Error(messageError)
    } catch (e: Exception) {
        return when (e) {
            is ConnectException -> {
                NetworkStatus.Error(CONNECT_EXCEPTION)
            }
            is UnknownHostException -> {
                NetworkStatus.Error(UNKNOWN_HOST_EXCEPTION)
            }
            is SocketTimeoutException -> {
                NetworkStatus.Error(SOCKET_TIME_OUT_EXCEPTION)
            }
            is HttpException -> {
                NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
            else -> {
                NetworkStatus.Error(UNKNOWN_NETWORK_EXCEPTION)
            }
        }
    }
}
