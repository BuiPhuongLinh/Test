package com.cmc.sharelocal

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long.utcToLocal(default: String = ""): String {
    try {
        val mDateFormat = "dd-MM HH:MM"
        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        cal.timeInMillis = this * 1000L
        var date = DateFormat.format(mDateFormat, cal.timeInMillis).toString()

        val formatter = SimpleDateFormat(mDateFormat, Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val value = formatter.parse(date)

        val dateFormatter = SimpleDateFormat(mDateFormat, Locale.getDefault())
        dateFormatter.timeZone = TimeZone.getDefault()
        if (value != null) {
            date = dateFormatter.format(value)
        } else {
            return default
        }
        return date
    } catch (exception: Exception) {
    }
    return default
}