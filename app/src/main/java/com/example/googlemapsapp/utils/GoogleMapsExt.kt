package com.example.googlemapsapp.utils

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

fun GoogleMap.centerMarker(latLng: LatLng){
    val builder = LatLngBounds.Builder().apply { include(latLng) }
    animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 15))
}