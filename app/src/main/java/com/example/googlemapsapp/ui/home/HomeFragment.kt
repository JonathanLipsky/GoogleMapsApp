package com.example.googlemapsapp.ui.home

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.googlemapsapp.R
import com.example.googlemapsapp.api.data.GooglePlacesResponse
import com.example.googlemapsapp.api.util.Status
import com.example.googlemapsapp.databinding.FragmentHomeBinding
import com.example.googlemapsapp.utils.centerMarker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class HomeFragment : Fragment() , OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mMap: GoogleMap
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        fusedLocationClient =  LocationServices.getFusedLocationProviderClient(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLocation()
        setUpMap()
        setupObserver(LatLng(37.733795,-122.446747))
    }

    private fun setupObserver(location: LatLng) {
        viewModel.getNearbyPlaces(location.latitude.toString(),location.longitude.toString()).observe(viewLifecycleOwner) { dataState ->
            dataState?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let {
                            handleResults(it.body())
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(activity?.applicationContext, it.message, Toast.LENGTH_LONG)
                            .show()
                    }
                    Status.LOADING -> {}
                }
            }
        }
    }

    private fun handleResults(response: GooglePlacesResponse?) {
        response?.results?.forEach {
            val marker = mMap.addMarker(MarkerOptions()
                .position(LatLng(it.geometry.location.lat, it.geometry.location.lng))
                .title(it.name))
            marker?.tag = it.placeId
        }
    }

    private fun setUpMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    private fun setUpLocation() {
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location: Location? ->
                var latitude = location?.latitude
                var longitude = location?.longitude
            }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMaxZoomPreference(14.0f)
        mMap.setOnMapLongClickListener { location ->
            onMapClick(location)
            googleMap.addMarker(
                MarkerOptions()
                    .position(location)
                    .title("")
            )
        }
        mMap.setOnMapLoadedCallback { mMap.centerMarker(LatLng(37.733795,-122.446747)) }
        mMap.setOnMarkerClickListener (this)
    }


    private fun onMapClick(latLng: LatLng) {
        val lat = latLng.latitude.toString()
        val lon = latLng.longitude.toString()
        viewModel.getWeather(lat, lon).observe(viewLifecycleOwner){ dataState ->
            dataState?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let {
                            Toast.makeText(activity?.applicationContext, "Success: ${it.body()}", Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(activity?.applicationContext, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> { }
                }
            }
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val placeId = marker.tag
        return true
    }
}