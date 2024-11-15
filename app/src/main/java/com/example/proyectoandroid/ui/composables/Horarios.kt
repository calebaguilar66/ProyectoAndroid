package com.example.proyectoandroid.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Horarios(modifier: Modifier = Modifier, navController: NavController){


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Horarios",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado").forEach{ day ->
                    TextButton(onClick = {} ) {
                        Text(day)
                    }
                }
            }


            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Seleccion Nivel")
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF2F2F2), shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Taller de Robotica", fontWeight = FontWeight.Bold)
                        Text(text = "Sala: 6", color = Color.Gray)
                    }
                    Text(text = "14:15 - 15:30", fontWeight = FontWeight.Medium)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF2F2F2), shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Big Data", fontWeight = FontWeight.Bold)
                        Text(text = "Sala: 207", color = Color.Gray)
                    }
                    Text(text = "18:40 - 20:00", fontWeight = FontWeight.Medium)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF2F2F2), shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Bases de Datos", fontWeight = FontWeight.Bold)
                        Text(text = "Sala: 206", color = Color.Gray)
                    }
                    Text(text = "20:10 - 21:30", fontWeight = FontWeight.Medium)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF2F2F2), shape = MaterialTheme.shapes.small)
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Programacion Android", fontWeight = FontWeight.Bold)
                        Text(text = "Sala: 6", color = Color.Gray)
                    }
                    Text(text = "21:40 - 23:00", fontWeight = FontWeight.Medium)
                }
            }



        }
    }


}

@Preview(showBackground = true)
@Composable
fun HorarioPreview(){
    Horarios(modifier = Modifier, navController = rememberNavController())
}