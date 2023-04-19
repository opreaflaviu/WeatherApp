package com.example.weatherapp.data.network.model

import java.util.Date

data class WeatherAlertPropertiesResponse(
    val event: String,
    val effective: Date,
    val ends: Date?,
    val senderName: String
)