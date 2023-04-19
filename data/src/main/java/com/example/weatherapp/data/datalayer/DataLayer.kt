package com.example.weatherapp.data.datalayer

import com.example.weatherapp.data.network.WeatherApi
import com.example.weatherapp.data.network.mapper.mapToDomainModel
import com.example.weatherapp.datalayer.IDataLayer
import com.example.weatherapp.model.Result
import com.example.weatherapp.model.WeatherAlertList
import com.example.weatherapp.model.getResult
import javax.inject.Inject

class DataLayer @Inject constructor(private val weatherApi: WeatherApi): IDataLayer {

    override suspend fun getWeatherAlerts(status: String, messageType: String): Result<WeatherAlertList> {
        return getResult { weatherApi.getWeatherAlerts(status, messageType).mapToDomainModel() }
    }
}