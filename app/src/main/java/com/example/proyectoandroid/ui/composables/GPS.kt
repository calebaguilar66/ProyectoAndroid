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
import com.example.proyectoandroid.data.PermissionsHelper
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import android.location.Location
import androidx.compose.material3.Button
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings


@Composable
fun GPSDireccion(modifier : Modifier, navController: NavController){
    val context = LocalContext.current

    var hasPermissions by remember { mutableStateOf(PermissionsHelper.hasLocationPermissions(context)) }

    LaunchedEffect(Unit) {
        if (!hasPermissions) {
            PermissionsHelper.requestLocationPermissions(context as Activity)
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasPermissions = permissions.all { it.value }
    }

    LaunchedEffect(Unit) {
        if (!hasPermissions) {
            launcher.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        }
    }


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



    if (hasPermissions) {
        val mapProperties = remember {
            MapProperties(isMyLocationEnabled = true)
        }
        val uiSettings = remember {
            MapUiSettings(zoomControlsEnabled = true)
        }
        var currentLocation by remember { mutableStateOf(LatLng(0.0, 0.0)) }

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        LaunchedEffect(key1 = true) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        currentLocation = LatLng(location.latitude, location.longitude)
                    }
                }
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties,
            uiSettings = uiSettings
        ) {
            Marker(
                state = MarkerState(position = currentLocation),
                title = "Mi ubicación",
                snippet = "Aquí estoy"
            )
            var savedLocations by remember { mutableStateOf<List<LatLng>>(emptyList()) }

            for (location in savedLocations) {
                Marker(
                    state = MarkerState(position = location),
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE) // Resaltar el marcador
                )
            }

            Marker(
                state = MarkerState(position = LatLng(-18.4833, -70.3102)),
                title = "Casino",
                snippet = "Chile"
            )
            Marker(
                state = MarkerState(position = LatLng(-18.4830, -70.3100)),
                title = "DAE",
                snippet = "Chile"
            )
            Marker(
                state = MarkerState(position = LatLng(-18.4834, -70.3100)),
                title = "Sala 6",
                snippet = "Chile"
            )
        }




    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Se necesitan permisos de ubicación para usar esta función.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
            }) {
                Text("Otorgar permisos")
            }
        }
    }

}







@Preview(showBackground = true)
@Composable
fun GPSPreview(){
    GPSDireccion(modifier = Modifier, navController = rememberNavController())
}