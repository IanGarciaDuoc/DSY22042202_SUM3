package com.example.vinachos.screens

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vinachos.CambioPasswordActivity
import com.example.vinachos.EscribirYHablarActivity
import com.example.vinachos.LoginActivity
import com.example.vinachos.R
import kotlinx.coroutines.delay
import com.example.vinachos.BuscarDispositivoActivity // Activity para la funcionalidad de buscar dispositivo
import com.example.vinachos.EscuchaPorMiActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen() {
    var expanded by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("Usuario") } // Valor predeterminado
    val context = LocalContext.current

    // Lógica de transición de imágenes
    var currentImageIndex by remember { mutableStateOf(0) }
    val images = listOf(R.drawable.vinachos, R.drawable.vinachos_red, R.drawable.vinachos_yellow)

    val transition = rememberInfiniteTransition(label = "imageTransition")
    val alpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "alphaAnimation"
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            currentImageIndex = (currentImageIndex + 1) % images.size
        }
    }

    fun vibrate(context: Context, isSuccess: Boolean) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val vibrationEffect = if (isSuccess) {
                    VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE)
                } else {
                    VibrationEffect.createWaveform(longArrayOf(0, 100, 50, 100), -1)
                }
                vibrator.vibrate(vibrationEffect)
            } else {
                if (isSuccess) {
                    vibrator.vibrate(300)
                } else {
                    vibrator.vibrate(longArrayOf(0, 100, 50, 100), -1)
                }
            }
        }
    }

    // Cargar el nombre del usuario desde SharedPreferences
    LaunchedEffect(Unit) {
        val sharedPreferences = context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        userName = sharedPreferences.getString("userName", "Usuario") ?: "Usuario"
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF0F0F0)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // Encabezado con el saludo y el menú
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF8B0000))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Hola, $userName",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Box(
                        modifier = Modifier
                            .clickable { expanded = !expanded }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menú",
                            modifier = Modifier.size(24.dp)
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(Color(0xFF8B0000))
                        ) {
                            DropdownMenuItem(
                                text = { Text("Cambiar Password") },
                                onClick = {
                                    vibrate(context, false)
                                    val intent = Intent(context, CambioPasswordActivity::class.java)
                                    context.startActivity(intent)
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Cambiar Password",
                                        tint = Color.Black
                                    )
                                }
                            )

                            DropdownMenuItem(
                                text = { Text("Cerrar sesión") },
                                onClick = {
                                    vibrate(context, false)
                                    (context as? ComponentActivity)?.finishAffinity()
                                    val intent = Intent(context, LoginActivity::class.java)
                                    context.startActivity(intent)
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.ExitToApp,
                                        contentDescription = "Cerrar sesión",
                                        tint = Color.Black
                                    )
                                }
                            )
                        }
                    }
                }
            }

            // Contenido principal con la imagen en transición
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = images[currentImageIndex]),
                    contentDescription = "Logo Vinachos",
                    modifier = Modifier
                        .size(150.dp)
                        .graphicsLayer(alpha = alpha)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Pronto más novedades",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Botón para Escribir y Hablar
                Button(
                    onClick = {
                        val intent = Intent(context, EscribirYHablarActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Habla por Mí",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Habla por Mí", fontSize = 18.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val intent = Intent(context, EscuchaPorMiActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000))
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Escucha por Mí",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Escucha por Mí", fontSize = 18.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))


                // Botón para Buscar Dispositivo (Geolocalización)
                Button(
                    onClick = {
                        val intent = Intent(context, BuscarDispositivoActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000))
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Obtener Ubicación",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Obtener Mí Ubicación", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
}
