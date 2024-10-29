package com.example.proyecto_android

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_android.sensors.CompassScreen
import com.example.proyecto_android.ui.theme.Proyecto_androidTheme


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
            Proyecto_androidTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") { Login(modifier = Modifier.padding(innerPadding), navController = navController) }
                        composable("protocolos") { Protocolos(modifier = Modifier.padding(innerPadding), navController = navController) }
                    }

                }
            }
            CompassScreen()
        }
    }
}

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
                Text("Login")
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
            .padding(16.dp)
    ){
        // Top Bar (Parte superior de la aplicacion)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(onClick = { navController.navigate("protocolos") }) {
                Text("Protocolos")
            }
            Button(onClick = { Usuario.cerrarSesion(navController) }) {
                Text("Cerrar sesión")
            }

        }
        Spacer(modifier = Modifier.height(70.dp))

        // Cuerpo de la ventana de Protocolos
        Text(
            text = "Protocolos de Seguridad",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .border(1.dp, Color.Green)
                .padding(8.dp)
        ){
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames.",
                fontSize = 14.sp
            )
        }
        Text(
            text = "Protocolos de Salud",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .border(1.dp, Color.Green)
                .padding(8.dp)
        ){
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames.",
                fontSize = 14.sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(1.dp, Color.Green)
        ){
            Text(
                "Video",
                modifier = Modifier.align(Alignment.Center)
            )
        }


    }
}



@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Proyecto_androidTheme {
        Login(navController = rememberNavController())
    }
}
@Preview(showBackground = true)
@Composable
fun ProtocolosPreview(){
    Proyecto_androidTheme {
        Protocolos(navController = rememberNavController())
    }
}


//solicitud de permisos en tiempo de ejecucion
private lateinit var locationPermissionLauncher: ActivityResultLauncher<String>

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    locationPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            startLocationUpdates()
        } else {
            // Manejar la falta de permisos
        }
    }

    requestLocationPermission()
}

private fun requestLocationPermission() {
    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        startLocationUpdates()
    } else {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}


