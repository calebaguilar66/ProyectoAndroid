package com.example.proyecto_android.sensors

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.lang.reflect.Modifier

@Composable
fun CompassScreen(viewModel: CompassViewModel = viewModel()) {
    val azimuth by viewModel.azimuth.collectAsState(initial = 0f)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Azimut: ${azimuth.toInt()}°",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
