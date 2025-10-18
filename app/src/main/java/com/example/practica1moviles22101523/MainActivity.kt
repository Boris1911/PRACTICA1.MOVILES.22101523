package com.example.practica1moviles22101523

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.practica1moviles22101523.ui.theme.PRACTICA1MOVILES22101523Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PRACTICA1MOVILES22101523Theme {
                Navigation()
            }
        }
    }
}
