package com.example.googlemapsapp.ui.home

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.googlemapsapp.R
import com.example.googlemapsapp.databinding.FragmentHomeBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class HomeFragment : Fragment() , OnMapReadyCallback {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mMap: GoogleMap
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLocation()
        setUpObserver()
        setUpMap()
    }

    private fun setUpMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    private fun setUpLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                var latitude = location?.latitude
                var longitude = location?.longitude
            }
    }

    private fun setUpObserver() {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
}