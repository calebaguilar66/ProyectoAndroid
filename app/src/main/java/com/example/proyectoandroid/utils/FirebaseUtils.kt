package com.example.proyectoandroid.utils

import com.google.firebase.auth.FirebaseAuth

object FirebaseUtils{

    fun autenticarUsuario(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    onSuccess()
                }else{
                    onError(task.exception?.message?: "Credenciales incorrectas. Vuelve a intentarlo")
                }
            }
    }

    fun registrarUsuario(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    onSuccess()
                }else{
                    onError(task.exception?.message ?: "Error al registrar usuario")
                }
            }
    }

    fun cerrarSesion(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        try {
            val auth = FirebaseAuth.getInstance()
            auth.signOut()
            onSuccess()
        }catch (e: Exception){
            onError(e.message ?: "Error desconocido al cerrar sesión")
        }

    }

}