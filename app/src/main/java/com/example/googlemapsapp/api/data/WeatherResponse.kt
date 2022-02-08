package com.example.googlemapsapp.api.data

 data class WeatherResponse(
     var weather: ArrayList<Weather>? = null,
     var main: Main? = null
){

    data class Weather(
        var id: Int = 0,
        var main: String? = null,
        var description: String? = null,
        var icon: String? = null
    )

     data class Main(
         var temp: Float = 0f,
         var humidity: Float = 0f,
         var pressure: Float = 0f,
         var temp_min: Float = 0f,
         var temp_max: Float = 0f,
     )
 }
