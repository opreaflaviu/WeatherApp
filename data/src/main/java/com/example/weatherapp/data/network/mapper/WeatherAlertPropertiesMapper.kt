package com.example.weatherapp.data.network.mapper

import com.example.weatherapp.data.network.model.WeatherAlertPropertiesResponse
import com.example.weatherapp.model.WeatherAlertProperties

internal fun WeatherAlertPropertiesResponse.mapToDomainModel() =
    WeatherAlertProperties(event, effective, ends, senderName)