package com.example.proyectoandroid.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoandroid.ui.theme.DarkGreen

@Composable
fun Noticias(modifier : Modifier, navController: NavController){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        TopBar(navController)

        Spacer(modifier = Modifier.height(26.dp))

        CartaDeInformacion(
            titulo = "Titulo de Noticias",
            contenido = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames."
        )

        Spacer(modifier = Modifier.height(20.dp))

        CartaDeInformacion(
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
@Preview(showBackground = true)
@Composable
fun NoticiasPreview(){
    Noticias(modifier = Modifier, navController = rememberNavController())
}