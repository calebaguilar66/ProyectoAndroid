package com.example.proyectoandroid.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HorarioDao {
    @Insert
    suspend fun insertHorario(horario: HorarioEntity)

    @Query("SELECT * FROM horarios")
    suspend fun listarHorarios(): List<HorarioEntity>
}