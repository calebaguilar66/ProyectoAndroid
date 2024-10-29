package com.example.proyecto_android.data

import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.proyecto_android.Manifest
import kotlinx.coroutines.launch

class NombreViewModel(application: Application): AndroidViewModel(application) {
    private val repository: NombreRepository
    private val _nombres = MutableLiveData<List<NombreEntity>>()

    init {
        val database = NombreDataBase.getDatabase(application.applicationContext)
        repository = NombreRepository(database)
    }

    fun insertName(nombre: NombreEntity) {
        viewModelScope.launch {
            repository.insertName(nombre)
            actualizarNombres()
        }
    }

    fun getUltimosNombres(): LiveData<List<NombreEntity>>{
        actualizarNombres()
        return _nombres
    }

    fun actualizarNombres(){
        viewModelScope.launch {
            val nombres = repository.getUltimosNombres()
            _nombres.value = nombres
        }
    }







}