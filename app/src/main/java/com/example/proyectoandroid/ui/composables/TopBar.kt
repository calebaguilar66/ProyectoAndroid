package com.example.proyectoandroid.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoandroid.Usuario
import com.example.proyectoandroid.ui.theme.DarkGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController){
    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ){
                Text(
                    "IPST",
                    color = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    TextButton(onClick = { navController.navigate("protocolos") }) {
                        Text("Protocolos", color = Color.White)
                    }
                    TextButton(onClick = { navController.navigate("gps") }) {
                        Text("GPS", color = Color.White)
                    }
                    TextButton(onClick = { navController.navigate("noticias") }) {
                        Text("Noticias", color = Color.White)
                    }
                    TextButton(onClick = { navController.navigate("horario") }) {
                        Text("Horarios", color = Color.White)
                    }
                    TextButton(onClick = { Usuario.cerrarSesion(navController) }) {
                        Text("Logout", color = Color.White)
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DarkGreen
        )
    )
}