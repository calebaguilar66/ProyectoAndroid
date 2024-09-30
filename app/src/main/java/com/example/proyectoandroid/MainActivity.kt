package com.example.proyectoandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoandroid.ui.theme.ProyectoAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Login(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo), null,
            modifier = Modifier.size(250.dp)
        )

        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¡Hola!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Inicia sesión y descubre")
            Text("todo lo nuevo que tenemos para ti!")
        }
        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(value="", onValueChange = {}, label = {
                Text("Ingrese correo electrónico")
            })
            Spacer(modifier = Modifier.height(7.dp))


            OutlinedTextField(value="", onValueChange = {}, label = {
                Text("Ingrese contraseña")
            })
            Spacer(modifier = Modifier.height(7.dp))
        }
        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

        }

    }

}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ProyectoAndroidTheme {
        Login()
    }
}
