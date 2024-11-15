package com.example.proyectoandroid.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
fun Protocolos(modifier: Modifier = Modifier, navController: NavController){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        TopBar(navController)

        Spacer(modifier = Modifier.height(40.dp))

        CartaDeInformacion(
            titulo = "Protocolos de seguridad",
            contenido = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac risus vel ligula lacinia ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames."
        )

        Spacer(modifier = Modifier.height(26.dp))

        CartaDeInformacion(
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

@Preview(showBackground = true)
@Composable
fun ProtocolosPreview(){
    Protocolos(navController = rememberNavController())
}