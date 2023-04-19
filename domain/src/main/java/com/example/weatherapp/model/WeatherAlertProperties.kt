package com.example.weatherapp.model

import java.util.Date
import java.util.concurrent.TimeUnit

data class WeatherAlertProperties(
    val event: String,
    val effective: Date,
    val ends: Date?,
    val senderName: String
) {
    fun getDurationMs(): Long = if (ends == null) 0 else ends.time - effective.time

    fun getDurationHours() = TimeUnit.MILLISECONDS.toHours(getDurationMs());

    fun getDurationDays() = TimeUnit.MILLISECONDS.toDays(getDurationMs());
}