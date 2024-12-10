package com.example.proyectoandroid.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.location.LocationServices

object LocationUtils {
    fun configureMap(context: Context, map: GoogleMap) {
        val defaultLocation = LatLng(-34.0, 151.0) // Ubicación inicial por defecto
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))

        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasFineLocationPermission) {
            try {
                map.isMyLocationEnabled = true

                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        val userLocation = LatLng(location.latitude, location.longitude)
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
                        map.addMarker(MarkerOptions().position(userLocation).title("Tu ubicación actual"))
                    }
                }
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }
}



