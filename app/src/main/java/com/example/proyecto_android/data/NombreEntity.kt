package com.example.proyecto_android.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "nombres")
data class NombreEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val name : String
)