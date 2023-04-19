package com.example.weatherapp.data.network.mapper

import com.example.weatherapp.data.network.model.WeatherAlertListResponse
import com.example.weatherapp.model.WeatherAlertList
import com.example.weatherapp.utils.logD

internal fun WeatherAlertListResponse.mapToDomainModel() =
    WeatherAlertList(features.map {
        logD("WeatherAlertListResponse.mapToDomainModel: $it")
        it.mapToDomainModel()
    })