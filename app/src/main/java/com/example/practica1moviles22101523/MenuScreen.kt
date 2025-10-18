package com.example.practica1moviles22101523

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("waterCalculator") }, modifier = Modifier.padding(8.dp)) {
            Text("Calculadora de consumo de agua")
        }
        Button(onClick = { navController.navigate("activityLog") }, modifier = Modifier.padding(8.dp)) {
            Text("Registro de actividad física")
        }
        Button(onClick = { navController.navigate("carCatalog") }, modifier = Modifier.padding(8.dp)) {
            Text("Catálogo de Autos deportivos")
        }
    }
}
