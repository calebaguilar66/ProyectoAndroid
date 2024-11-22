package com.example.proyectoandroid.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.proyectoandroid.data.HorarioViewModel

@Composable
fun Horarios(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HorarioViewModel
) {
    val horarios by viewModel.getHorarios().observeAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(navController)

        IconButton(
            onClick = { navController.navigate("formularioHorarios") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Horario")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(horarios) { horario ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF2F2F2))
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = horario.asignatura, fontWeight = FontWeight.Bold)
                        Text(text = "Sala: ${horario.ubicacion}", color = Color.Gray)
                    }
                    Text(text = "${horario.horarioEntrada} - ${horario.horarioSalida}", fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}
