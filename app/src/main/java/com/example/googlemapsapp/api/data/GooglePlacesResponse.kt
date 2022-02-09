package com.example.googlemapsapp.api.data

import com.google.gson.annotations.SerializedName

data class GooglePlacesResponse(
    val results: ArrayList<Result>
) {

    data class Result(
        val name: String,
        val rating: Float,
        @SerializedName("place_id")
        val placeId: String,
        val geometry: Geometry
    )

    data class Geometry(
        val location: Location)

    data class Location(
        val lat: Double,
        val lng: Double
    )

}
