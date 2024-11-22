package com.example.proyectoandroid.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoandroid.data.HorarioEntity
import com.example.proyectoandroid.data.HorarioViewModel

@Composable
fun FormularioHorarios(navController: NavController, viewModel: HorarioViewModel) {
    var asignatura by remember { mutableStateOf("") }
    var horarioEntrada by remember { mutableStateOf("") }
    var horarioSalida by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = asignatura,
            onValueChange = { asignatura = it },
            label = { Text("Asignatura") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = horarioEntrada,
            onValueChange = { horarioEntrada = it },
            label = { Text("Horario de Entrada") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = horarioSalida,
            onValueChange = { horarioSalida = it },
            label = { Text("Horario de Salida") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = ubicacion,
            onValueChange = { ubicacion = it },
            label = { Text("Sala") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.insertHorario(
                    HorarioEntity(
                        asignatura = asignatura,
                        horarioEntrada = horarioEntrada,
                        horarioSalida = horarioSalida,
                        ubicacion = ubicacion.toIntOrNull() ?: 0,
                        fecha = fecha.ifEmpty { null }
                    )
                )
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}