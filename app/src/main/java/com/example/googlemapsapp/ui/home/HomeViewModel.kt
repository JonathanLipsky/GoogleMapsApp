package com.example.googlemapsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.googlemapsapp.api.WeatherApiClient
import com.example.googlemapsapp.api.util.DataState

class HomeViewModel : ViewModel() {

    fun getWeather(lat: String, lon: String) = liveData(viewModelScope.coroutineContext) {
        emit(DataState.loading(data = null))
        try {
            emit(DataState.success(data = WeatherApiClient.getWeather(lat, lon)))
        } catch (exception: Exception) {
            emit(DataState.error(data = null, message = exception.message ?: "Error occured"))
        }
    }
}