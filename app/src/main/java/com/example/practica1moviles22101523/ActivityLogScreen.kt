package com.example.practica1moviles22101523

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ActivityLogScreen(navController: NavController) {
    var activityType by remember { mutableStateOf("Correr") }
    var duration by remember { mutableStateOf("") }
    var intensity by remember { mutableStateOf("Media") }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var expanded by remember { mutableStateOf(false) }
        val activities = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")
        Box {
            Button(onClick = { expanded = true }) {
                Text(activityType)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                activities.forEach { activity ->
                    DropdownMenuItem(
                        text = { Text(activity) },
                        onClick = {
                            activityType = activity
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duración en minutos") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Intensidad:")
            RadioButton(selected = intensity == "Baja", onClick = { intensity = "Baja" })
            Text("Baja")
            RadioButton(selected = intensity == "Media", onClick = { intensity = "Media" })
            Text("Media")
            RadioButton(selected = intensity == "Alta", onClick = { intensity = "Alta" })
            Text("Alta")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (duration.isBlank()) {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val durationValue = duration.toIntOrNull()
            if (durationValue == null || durationValue <= 0) {
                Toast.makeText(context, "La duración debe ser un número entero positivo", Toast.LENGTH_SHORT).show()
                return@Button
            }

            val caloriesPerMinute = when (activityType) {
                "Correr" -> 10
                "Caminar" -> 5
                "Nadar" -> 8
                "Ciclismo" -> 7
                "Yoga" -> 4
                else -> 0
            }

            val intensityFactor = when (intensity) {
                "Baja" -> 0.8
                "Media" -> 1.0
                "Alta" -> 1.2
                else -> 1.0
            }

            val caloriesBurned = caloriesPerMinute * durationValue * intensityFactor
            result = String.format("Calorías quemadas: %.2f", caloriesBurned)

        }) {
            Text("Calcular")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (result.isNotEmpty()) {
            Text(result)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Regresar al menú principal")
        }
    }
}