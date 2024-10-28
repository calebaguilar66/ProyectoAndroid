package com.example.proyectoandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectoandroid.ui.theme.DarkGreen
import com.example.proyectoandroid.ui.theme.ProyectoAndroidTheme

//Clase Usuario con respectivas funciones de iniciar sesion y cerrar sesion

class Usuario(val email: String, val password: String){
    companion object{
        private val usuarios = listOf(
            Usuario("admin@gmail.cl", "admin"),
            Usuario("caleb@gmail.cl", "caleb"),
            Usuario("claudio@gmail.cl", "claudio")
        )
        fun iniciarSesion(correo: String, contrasena: String): Boolean{
            return usuarios.find { it.email == correo && it.password == contrasena } != null
        }

        fun cerrarSesion(navController: NavController){
            navController.navigate("login"){
                popUpTo("login") { inclusive = true }
            }
        }

    }
}



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoAndroidTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") { Login(modifier = Modifier.padding(innerPadding), navController = navController) }
                        composable("protocolos") { Protocolos(modifier = Modifier.padding(innerPadding), navController = navController) }
                    }
                }
            }
        }
    }
}
//Ventana de Inicio de Sesion
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
            Button(onClick = {
                when{
                    correo.isBlank() || contrasena.isBlank() ->{
                        mensaje = "Rellene los campos, por favor."
                    }
                    Usuario.iniciarSesion(correo, contrasena) ->{
                        navController.navigate("protocolos")
                    }
                    else -> {
                        mensaje = "Credenciales incorrectas"
                    }
                }
            }){
                Text("Login", color = Color.White)
            }

        }

    }

}
//Ventana de Protocolos de Seguridad y Salud
@Composable
fun Protocolos(modifier: Modifier = Modifier, navController: NavController){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(navController)
        
        Spacer(modifier = Modifier.height(40.dp))

        CartaProtocolo(
            titulo = "Protocolos de seguridad",
            contenido = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames."
        )

        Spacer(modifier = Modifier.height(26.dp))

        CartaProtocolo(
            titulo = "Protocolos de salud",
            contenido = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames."
        )

        Spacer(modifier = Modifier.height(26.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(200.dp)
                .border(1.dp, DarkGreen)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ){
            Text(
                "Video",
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController){
    TopAppBar(
        title = { Text("IPST", color = Color.White)},
        actions = {
            TextButton(onClick = {navController.navigate("protocolos") }) {
                Text("Protocolos", color = Color.White)
            }
            TextButton(onClick = {Usuario.cerrarSesion(navController) }) {
                Text("Cerrar Sesion", color = Color.White)
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DarkGreen
        )
    )
}

@Composable
fun CartaProtocolo(titulo : String, contenido: String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, DarkGreen)
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(
            text = titulo,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkGreen,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = contenido,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ProyectoAndroidTheme {
        Login(navController = rememberNavController())
    }
}
@Preview(showBackground = true)
@Composable
fun ProtocolosPreview(){
    ProyectoAndroidTheme {
        Protocolos(navController = rememberNavController())
    }
}

