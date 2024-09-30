package com.example.vinachos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vinachos.screens.CambioPasswordScreen
import com.example.vinachos.ui.theme.DyfTheme

class CambioPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DyfTheme {
                CambioPasswordScreen()
            }
        }
    }
}
