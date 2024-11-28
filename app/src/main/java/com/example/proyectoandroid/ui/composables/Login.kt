package com.example.proyectoandroid.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoandroid.R
import com.example.proyectoandroid.utils.FirebaseUtils.autenticarUsuario


@Composable
fun Login(modifier: Modifier = Modifier, navController: NavController) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

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

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¡Hola!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Inicia sesión y descubre")
            Text("todo lo nuevo que tenemos para ti!")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                value= correo,
                onValueChange = { correo = it },
                label = { Text("Ingrese correo electrónico")
                })
            Spacer(modifier = Modifier.height(7.dp))


            OutlinedTextField(
                value= contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Ingrese contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(7.dp))

            if(mensaje.isNotEmpty()){
                Text(
                    text = mensaje,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

        }
        Column(
            modifier = modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                onClick = {
                    autenticarUsuario(correo, contrasena, onSuccess = {
                        navController.navigate("protocolos")
                    }, onError = { error ->
                        mensaje = error
                    })
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¿No tienes cuenta? Regístrate",
                color = Color.Blue,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        navController.navigate("registro")
                    }
            )

        }

    }

}
@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    Login(navController = rememberNavController())
}