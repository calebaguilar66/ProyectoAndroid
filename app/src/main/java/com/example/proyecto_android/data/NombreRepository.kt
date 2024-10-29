package com.example.proyecto_android.data

class NombreRepository(private val database: NombreDataBase){
    suspend fun insertName(name: NombreEntity){
        database.nombreDao().insertNombre(name)
    }

    suspend fun getUltimosNombres(): List<NombreEntity> {
        return database.nombreDao().getUltimosNombres()
    }
}