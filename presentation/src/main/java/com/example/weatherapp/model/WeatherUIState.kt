package com.example.weatherapp.model

sealed class WeatherUIState {

    object Loading : WeatherUIState()

    object Error : WeatherUIState()

    data class ShowWeatherAlerts(val weatherAlerts: WeatherAlertList): WeatherUIState()
}