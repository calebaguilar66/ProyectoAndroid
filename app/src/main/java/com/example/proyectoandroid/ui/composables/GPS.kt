package com.example.proyectoandroid.ui.composables

import android.Manifest
import android.content.pm.PackageManager
import android.view.View
import android.widget.FrameLayout
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectoandroid.utils.LocationUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun GPSDireccion(modifier: Modifier = Modifier, navController: NavController) {
    var destino by remember { mutableStateOf("") }
    var sugerencias by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }

    // Contexto y estado del permiso
    val context = LocalContext.current
    var isPermissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val db = FirebaseFirestore.getInstance()

    // Lanzador para solicitar permisos
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        isPermissionGranted = granted
    }

    // Solicitar permisos si no están concedidos
    LaunchedEffect(Unit) {
        if (!isPermissionGranted) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    LaunchedEffect(destino) {
        if (destino.isNotBlank()) {
            // Consultar Firestore para sugerencias
            val querySnapshot = db.collection("ubicaciones")
                .whereGreaterThanOrEqualTo("nombre", destino)
                .whereLessThanOrEqualTo("nombre", destino + '\uf8ff')
                .get()
                .await()

            sugerencias = querySnapshot.documents.mapNotNull { doc ->
                val nombre = doc.getString("nombre")
                val lat = doc.getDouble("latitud")
                val lng = doc.getDouble("longitud")
                if (nombre != null && lat != null && lng != null) {
                    mapOf("nombre" to nombre, "latitud" to lat, "longitud" to lng)
                } else null
            }
        }
    }

    TopBar(navController)

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Campo de búsqueda del destino
        OutlinedTextField(
            value = destino,
            onValueChange = { destino = it },
            label = { Text("Ingrese destino") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Mostrar sugerencias
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            sugerencias.forEach { ubicacion ->
                val nombre = ubicacion["nombre"] as String
                val lat = ubicacion["latitud"] as Double
                val lng = ubicacion["longitud"] as Double

                Text(
                    text = nombre,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .clickable {
                            selectedLocation = LatLng(lat, lng)
                            destino = nombre
                        }
                )
            }
        }

        // Mostrar el mapa o el mensaje de permisos según el estado
        if (isPermissionGranted) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                MapScreen(selectedLocation)
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("Se necesitan permisos de ubicación para usar el mapa.")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }) {
                    Text("Otorgar permisos")
                }
            }
        }
    }
}




@Composable
fun MapScreen(selectedLocation: LatLng?) {
    AndroidView(
        factory = { context ->
            val frameLayout = FrameLayout(context).apply { id = View.generateViewId() }
            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            val mapFragment = SupportMapFragment.newInstance()

            frameLayout.post {
                fragmentManager.beginTransaction()
                    .replace(frameLayout.id, mapFragment, "map_fragment")
                    .commitNow()

                mapFragment.getMapAsync { googleMap ->
                    LocationUtils.configureMap(context, googleMap)

                    // Mover el mapa si hay una ubicación seleccionada
                    if (selectedLocation != null) {
                        googleMap.clear() // Limpiar marcadores anteriores
                        googleMap.addMarker(
                            MarkerOptions()
                                .position(selectedLocation)
                                .title("Destino seleccionado")
                        )
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLocation, 15f))
                    }
                }
            }

            frameLayout
        },
        modifier = Modifier.fillMaxSize()
    )
}


@Preview(showBackground = true)
@Composable
fun GPSPreview(){
    GPSDireccion(modifier = Modifier, navController = rememberNavController())
}