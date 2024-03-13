package com.example.firetest.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.firetest.R
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun getGreeting(
    context: Context
): String {
    val now = LocalTime.now()
    val hour = now.hour
    return when {
        hour < 6 -> context.getString(R.string.good_night)
        hour < 12 -> context.getString(R.string.good_morning)
        hour < 18 -> context.getString(R.string.good_afternoon)
        else -> context.getString(R.string.good_evening)
    }
}
fun getCurrentDate(): String {
    val currentDate = Date() // Get current date
    val dateFormat = SimpleDateFormat("EEE, d'th' MMM yyyy", Locale.ENGLISH)
    return dateFormat.format(currentDate)
}
