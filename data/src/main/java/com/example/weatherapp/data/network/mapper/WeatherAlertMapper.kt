package com.example.weatherapp.data.network.mapper

import com.example.weatherapp.data.network.model.WeatherAlertResponse
import com.example.weatherapp.model.WeatherAlert

internal fun WeatherAlertResponse.mapToDomainModel() =
    WeatherAlert(id, properties.mapToDomainModel())