package com.example.proyectoandroid.data

class HorarioRepository(private val db: HorarioDatabase) {
    suspend fun insertHorario(horario: HorarioEntity){
        db.horarioDao().insertHorario(horario)
    }

    suspend fun listarHorarios(): List<HorarioEntity>{
        return db.horarioDao().listarHorarios()
    }

}