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
                        composable("gps") { GPSDireccion(modifier = Modifier.padding(innerPadding), navController = navController) }
                        composable("noticias") { Noticias(modifier = Modifier.padding(innerPadding), navController = navController) }
                        composable("horario") { Horarios(modifier = Modifier.padding(innerPadding), navController = navController) }
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


@Composable
fun Noticias(modifier : Modifier, navController: NavController){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        TopBar(navController)

        Spacer(modifier = Modifier.height(26.dp))

        CartaProtocolo(
            titulo = "Titulo de Noticias",
            contenido = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames."
        )

        Spacer(modifier = Modifier.height(20.dp))

        CartaProtocolo(
            titulo = "Importante",
            contenido = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames."
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(DarkGreen)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "(Nombre de Usuario)",
                    color = Color.White
                )
                Text(
                    text = "Jefatura de Carrera",
                    color = Color.White
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "21/12/2024",
                    color = Color.White
                )
                Text(
                    text = "8:45",
                    color = Color.White
                )
            }
        }
    }
}

//Ventana de Como llegar a ciertos lugares dentro del establecimiento
@Composable
fun GPSDireccion(modifier : Modifier, navController: NavController){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(navController)

        Spacer(modifier = Modifier.height(36.dp))

        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFFF5F5F5))
                .border(1.dp, DarkGreen)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Origen
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.gps),
                    contentDescription = null,
                    tint = DarkGreen,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Ingrese origen") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //Destino
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.destino),
                    contentDescription = null,
                    tint = DarkGreen,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Ingrese destino") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 16.dp)
                .border(1.dp, DarkGreen)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Vista previa del mapa",
                color = Color.Gray
            )
        }
    }

}




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
@Preview(showBackground = true)
@Composable
fun GPSDireccionesPreview(){
    ProyectoAndroidTheme {
        GPSDireccion(modifier = Modifier, navController = rememberNavController())
    }
}
@Preview(showBackground = true)
@Composable
fun NoticiasPreview(){
    ProyectoAndroidTheme {
        Noticias(modifier = Modifier, navController = rememberNavController())
    }
}
@Preview(showBackground = true)
@Composable
fun HorariosPreview(){
    ProyectoAndroidTheme {
        Horarios(modifier = Modifier, navController = rememberNavController())
    }
}
