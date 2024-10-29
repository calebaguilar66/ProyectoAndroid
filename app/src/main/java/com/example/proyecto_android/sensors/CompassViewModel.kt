package com.example.proyecto_android.sensors

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class CompassViewModel(application: Application) : AndroidViewModel(application) {
    private val compass = Compass(application)
    val azimuth: LiveData<Float> = compass.azimuth
}
