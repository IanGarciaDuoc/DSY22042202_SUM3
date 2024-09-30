package com.example.vinachos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vinachos.screens.EscribirYHablarScreen

class EscribirYHablarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EscribirYHablarScreen()
        }
    }
}
