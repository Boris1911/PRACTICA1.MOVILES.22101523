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
fun WaterCalculatorScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Sin especificar") }
    var result by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre de la persona") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Peso corporal (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Género:")
            RadioButton(selected = gender == "Masculino", onClick = { gender = "Masculino" })
            Text("Masculino")
            RadioButton(selected = gender == "Femenino", onClick = { gender = "Femenino" })
            Text("Femenino")
            RadioButton(selected = gender == "Sin especificar", onClick = { gender = "Sin especificar" })
            Text("Sin especificar")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (name.isBlank() || weight.isBlank()) {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val weightValue = weight.toDoubleOrNull()
            if (weightValue == null || weightValue <= 5 || weightValue > 200) {
                Toast.makeText(context, "El peso debe ser un número positivo entre 5 y 200", Toast.LENGTH_SHORT).show()
                return@Button
            }

            val genderFactor = when (gender) {
                "Masculino" -> 1.02
                "Femenino" -> 1.01
                else -> 1.0
            }

            val recommendedLitters = weightValue * 0.035 * genderFactor
            result = String.format("%s debe beber aproximadamente %.2f litros de agua al día", name, recommendedLitters)

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
