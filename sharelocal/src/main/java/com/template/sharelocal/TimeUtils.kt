package com.template.sharelocal

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Long.UTCtoLocal(): String {

    val mDateFormate = "dd-MM HH:MM"
    val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    cal.timeInMillis = this * 1000L
    var date = DateFormat.format(mDateFormate, cal.timeInMillis).toString()

    val formatter = SimpleDateFormat(mDateFormate, Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val value = formatter.parse(date)

    val dateFormatter = SimpleDateFormat(mDateFormate, Locale.getDefault())
    dateFormatter.timeZone = TimeZone.getDefault()
    date = dateFormatter.format(value)
    return date
}