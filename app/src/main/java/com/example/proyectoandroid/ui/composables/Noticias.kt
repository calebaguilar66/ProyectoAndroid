package com.example.proyectoandroid.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoandroid.ui.theme.DarkGreen
import com.example.proyectoandroid.utils.FirestoreUtils

@Composable
fun Noticias(modifier: Modifier = Modifier, navController: NavController) {
    // Estado para almacenar las noticias
    var noticias by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Cargar las noticias desde Firestore
    LaunchedEffect(Unit) {
        FirestoreUtils.obtenerNoticias(
            onSuccess = { noticias = it },
            onError = { errorMessage = it }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Barra superior (TopBar)
        TopBar(navController)

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar noticias
        IconButton(
            onClick = { navController.navigate("formularioNoticias") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Noticia")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar error o la lista de noticias
        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        } else if (noticias.isEmpty()) {
            Text(
                text = "No hay noticias disponibles.",
                color = DarkGreen,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            // Lista de noticias
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(noticias.size) { index ->
                    val noticia = noticias[index]
                    CartaDeInformacion(
                        titulo = noticia["titulo"]?.toString() ?: "Sin título",
                        contenido = noticia["contenido"]?.toString() ?: "Sin contenido"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoticiasPreview() {
    Noticias(modifier = Modifier, navController = rememberNavController())
}
