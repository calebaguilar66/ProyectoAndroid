package com.example.proyectoandroid.utils

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreUtils {
    private val db = FirebaseFirestore.getInstance()

    // Agregar una noticia
    fun agregarNoticia(
        titulo: String,
        contenido: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val noticia = hashMapOf(
            "titulo" to titulo,
            "contenido" to contenido,
            "fechaPublicacion" to System.currentTimeMillis()
        )
        db.collection("Noticias").add(noticia)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onError(exception.message ?: "Error desconocido") }
    }


    // Obtener noticias
    fun obtenerNoticias(
        onSuccess: (List<Map<String, Any>>) -> Unit,
        onError: (String) -> Unit
    ) {
        db.collection("Noticias").get()
            .addOnSuccessListener { result ->
                val noticias = result.map { it.data }
                onSuccess(noticias)
            }
            .addOnFailureListener { exception -> onError(exception.message ?: "Error desconocido") }
    }


    fun actualizarNoticia(
        id: String,
        titulo: String,
        contenido: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        db.collection("Noticias").document(id).update("titulo", titulo, "contenido", contenido)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onError(exception.message ?: "Error desconocido") }
    }



    fun eliminarNoticia(
        id: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        db.collection("Noticias").document(id).delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onError(exception.message ?: "Error desconocido") }
    }




}