package com.example.weatherapp.utils

import android.content.Context
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherAlertProperties
import java.text.SimpleDateFormat
import java.util.Locale

internal fun WeatherAlertProperties.formatDate(context: Context): String {
    return if (getDurationDays() >= 1) {
        val start = SimpleDateFormat("hh:mm dd MMM", Locale.US).format(this@formatDate.effective)
        val ends = SimpleDateFormat("hh:mm dd MMM", Locale.US).format(this@formatDate.ends)
        String.format(context.getString(R.string.event_period_many_day), start, ends, getDurationHours().toString())
    } else if(this@formatDate.ends == null) {
        val start = SimpleDateFormat("hh:mm dd MMM", Locale.US).format(this@formatDate.effective)
        String.format(context.getString(R.string.event_period_without_end_date), start)
    } else {
        val startHour = SimpleDateFormat("hh:mm", Locale.US).format(this@formatDate.effective)
        val endHour = SimpleDateFormat("hh:mm", Locale.US).format(this@formatDate.ends)
        val day = SimpleDateFormat("dd MMM", Locale.US).format(this@formatDate.ends)

        if (getDurationHours() > 1) {
            String.format(context.getString(R.string.event_period_1_day_hours), startHour, endHour, day, getDurationHours().toString())
        } else {
            String.format(context.getString(R.string.event_period_1_day_1_hour), startHour, endHour, day)
        }
    }
}