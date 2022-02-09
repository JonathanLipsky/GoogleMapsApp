package com.example.googlemapsapp.api

import com.example.googlemapsapp.api.data.GooglePlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesApiService {

    @GET("nearbysearch/json")
    suspend fun getNearbyRestaurants(@Query("location") location: String,
                                     @Query("radius") radius: String,
                                     @Query("type") type: String,
                                     @Query("key") apiKey: String = API_KEY): Response<GooglePlacesResponse>

}

private val API_KEY = "AIzaSyBzXWn_x5NVBQt-Y-vuD01BXzN5BmPfHQk"


