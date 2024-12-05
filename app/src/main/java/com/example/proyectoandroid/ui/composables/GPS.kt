package com.example.proyectoandroid.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoandroid.ui.theme.DarkGreen
import com.example.proyectoandroid.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


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

        val cameraPositionState = rememberCameraPositionState()


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 16.dp)
            .border(1.dp, DarkGreen),
    ) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = LatLng(40.7128, -74.0060)), // Ejemplo: Nueva York
                title = "Nueva York"
            )
        }
    }


    }


//    val cameraPositionState = rememberCameraPositionState()


//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(300.dp)
//            .padding(horizontal = 16.dp)
//            .border(1.dp, DarkGreen),
//    ) {
//        GoogleMap(
//            modifier = Modifier.matchParentSize(),
//            cameraPositionState = cameraPositionState
//        ) {
//            Marker(
//                state = MarkerState(position = LatLng(40.7128, -74.0060)), // Ejemplo: Nueva York
//                title = "Nueva York"
//            )
//        }
//    }
}





@Preview(showBackground = true)
@Composable
fun GPSPreview(){
    GPSDireccion(modifier = Modifier, navController = rememberNavController())
}