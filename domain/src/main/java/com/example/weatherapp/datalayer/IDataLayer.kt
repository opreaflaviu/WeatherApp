package com.example.weatherapp.datalayer

import com.example.weatherapp.model.Result
import com.example.weatherapp.model.WeatherAlertList

interface IDataLayer {

    suspend fun getWeatherAlerts(status: String, messageType: String): Result<WeatherAlertList>
}