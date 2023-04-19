package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.datalayer.DataLayer
import com.example.weatherapp.model.Result.Error
import com.example.weatherapp.model.Result.Success
import com.example.weatherapp.model.WeatherUIState
import com.example.weatherapp.utils.logD
import com.example.weatherapp.utils.logE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(private val dataLayer: DataLayer): ViewModel() {

    private val _uiState = MutableLiveData<WeatherUIState>(WeatherUIState.Loading)
    val uiState: LiveData<WeatherUIState> get() = _uiState

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = WeatherUIState.Error
    }

    init {
        loadWeatherAlerts()
    }
    
    private fun loadWeatherAlerts() {
        logD("loadWeatherAlerts")
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = WeatherUIState.Loading
            val response = dataLayer.getWeatherAlerts(AlertStatusType.ACTUAL.statusType, AlertMessageType.ALERT.messageType)
            _uiState.value = when(response) {
                is Success -> WeatherUIState.ShowWeatherAlerts(response.data)
                is Error -> {
                    logE("Error while loading weather alerts: ${response.throwable.message}", response.throwable)
                    WeatherUIState.Error
                }
            }
        }
    }
}