package com.example.proyectoandroid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    HorarioEntity::class],
    version = 1,
    exportSchema = false
)

abstract class HorarioDatabase : RoomDatabase(){
    abstract fun horarioDao(): HorarioDao

    companion object{
        @Volatile
        private var INSTANCE: HorarioDatabase? = null


        fun getDatabase(context: Context): HorarioDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HorarioDatabase::class.java,
                    "horarios_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}