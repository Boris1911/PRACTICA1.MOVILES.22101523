package com.example.practica1moviles22101523

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

data class Car(
    val marca: String,
    val modelo: String,
    val precio: Double,
    val imageUrl: String
)

fun mockCarData(): List<Car> {
    return listOf(
        Car("Ferrari", "SF90 Stradale", 507000.0, "https://loremflickr.com/400/200/sportscar,ferrari?random=1"),
        Car("Lamborghini", "Aventador SVJ", 517770.0, "https://loremflickr.com/400/200/sportscar,lamborghini?random=2"),
        Car("Porsche", "911 GT3 RS", 223800.0, "https://loremflickr.com/400/200/sportscar,porsche?random=3"),
        Car("McLaren", "720S", 299000.0, "https://loremflickr.com/400/200/sportscar,mclaren?random=4"),
        Car("Bugatti", "Chiron", 3000000.0, "https://loremflickr.com/400/200/sportscar,bugatti?random=5")
    )
}

@Composable
fun CarCatalogScreen(navController: NavController) {
    val carList = mockCarData()
    val totalCost = carList.sumOf { it.precio }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(carList) { car ->
                CarCard(car = car)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = String.format("Costo total de todos los autos: $%,.2f", totalCost),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Regresar al menú principal")
        }
    }
}

@Composable
fun CarCard(car: Car) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = car.imageUrl,
                contentDescription = "Imagen de ${car.marca} ${car.modelo}",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${car.marca} ${car.modelo}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = String.format("Precio: $%,.2f", car.precio), fontSize = 16.sp)
        }
    }
}
