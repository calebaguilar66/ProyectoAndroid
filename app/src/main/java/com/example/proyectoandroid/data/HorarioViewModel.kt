package com.example.proyectoandroid.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HorarioViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HorarioRepository
    private val _horarios = MutableLiveData<List<HorarioEntity>>()

    init {
        val database = HorarioDatabase.getDatabase(application.applicationContext)
        repository = HorarioRepository(database)
    }

    fun insertHorario(horario: HorarioEntity){
        viewModelScope.launch {
            repository.insertHorario(horario)
            actualizarHorarios()

        }
    }
    fun getHorarios(): LiveData<List<HorarioEntity>>{
        actualizarHorarios()
        return _horarios

    }
    private fun actualizarHorarios(){
        viewModelScope.launch {
            val horarios = repository.listarHorarios()
            _horarios.value = horarios
        }
    }

}