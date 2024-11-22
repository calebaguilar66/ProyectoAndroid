package com.example.proyectoandroid

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectoandroid.ui.theme.ProyectoAndroidTheme
import com.example.proyectoandroid.ui.composables.*
import com.example.proyectoandroid.data.HorarioViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoAndroidTheme {
                PantallaPrincipal()
            }
        }
    }
}


@Composable
fun PantallaPrincipal() {

    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext as Application
    val horarioViewModel = remember { HorarioViewModel(context) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { Login(modifier = Modifier.padding(innerPadding), navController = navController) }
            composable("protocolos") { Protocolos(modifier = Modifier.padding(innerPadding), navController = navController) }
            composable("gps") { GPSDireccion(modifier = Modifier.padding(innerPadding), navController = navController) }
            composable("noticias") { Noticias(modifier = Modifier.padding(innerPadding), navController = navController) }
            composable("horario") { Horarios(modifier = Modifier.padding(innerPadding), navController = navController, viewModel = horarioViewModel) }
            composable("formularioHorarios"){ FormularioHorarios(navController = navController, viewModel = horarioViewModel) }
        }
    }
}
