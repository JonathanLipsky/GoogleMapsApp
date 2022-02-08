package com.example.googlemapsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.googlemapsapp.api.WeatherApiClient
import com.example.googlemapsapp.api.util.DataState
import kotlinx.coroutines.Dispatchers

class HomeViewModel : ViewModel() {

    fun getWeather(lat: String, lon: String) = liveData(Dispatchers.IO) {
        emit(DataState.loading(data = null))
        try {
            emit(DataState.success(data=WeatherApiClient.getWeather(lat, lon)))
        }catch (exception: Exception){
            emit(DataState.error(data=null,message = exception.message?:"Error occured"))
        }
    }
}