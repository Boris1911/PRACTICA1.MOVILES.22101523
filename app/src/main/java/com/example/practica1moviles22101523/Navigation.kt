package com.example.practica1moviles22101523

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { MenuScreen(navController) }
        composable("waterCalculator") { WaterCalculatorScreen(navController) }
        composable("activityLog") { ActivityLogScreen(navController) }
        composable("carCatalog") { CarCatalogScreen(navController) }
    }
}
