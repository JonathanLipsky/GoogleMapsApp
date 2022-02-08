package com.example.googlemapsapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getWeather(@Query("lat") latitude: String,
                           @Query("lon") longitude: String,
                           @Query("appid") apiKey: String = API_KEY): Response<Unit>

}

private val API_KEY = "16d21b081b50ef7be797d58b6bc4b73b"
