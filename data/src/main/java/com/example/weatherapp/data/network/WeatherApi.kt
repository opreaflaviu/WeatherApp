package com.example.weatherapp.data.network

import com.example.weatherapp.data.network.model.WeatherAlertListResponse
import com.example.weatherapp.data.network.model.WeatherAlertResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * TODO: Comment
 *
 * @author flaviuoprea on 16.04.2023
 */
interface WeatherApi {

    companion object {
        const val BASE_URL = "https://api.weather.gov/"
    }

    @GET("alerts/active")
    suspend fun getWeatherAlerts(
        @Query("status") status: String,
        @Query("message_type") messageType: String
    ): WeatherAlertListResponse

}