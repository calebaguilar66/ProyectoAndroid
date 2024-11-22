package com.example.proyectoandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horarios")
data class HorarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val asignatura: String,
    val horarioEntrada: String,
    val horarioSalida: String,
    val ubicacion: Int,
    val fecha: String? = null
)
