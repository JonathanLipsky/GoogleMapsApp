package com.example.googlemapsapp.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import pub.devrel.easypermissions.EasyPermissions

object LocationPermissionUtil {

    fun hasLocationPermission(context: Context): Boolean {
        return EasyPermissions.hasPermissions(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

}