package com.example.proyectoandroid

import androidx.navigation.NavController

class Usuario(val email: String, val password: String){
    companion object{
        private val usuarios = listOf(
            Usuario("admin@gmail.cl", "admin"),
            Usuario("caleb@gmail.cl", "caleb"),
            Usuario("claudio@gmail.cl", "claudio")
        )
        fun iniciarSesion(correo: String, contrasena: String): Boolean{
            return usuarios.find { it.email == correo && it.password == contrasena } != null
        }

        fun cerrarSesion(navController: NavController){
            navController.navigate("login"){
                popUpTo("login") { inclusive = true }
            }
        }

    }
}