package com.example.location

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.location.ui.theme.LocationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           val locationViewModel= LocationViewModel()
            LocationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding),
                        locationViewModel = locationViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun App( modifier: Modifier = Modifier,locationViewModel: LocationViewModel) {
    val context = LocalContext.current
    val helperClass = HelperClass(context)

    Display(
        modifier = Modifier,
        helperClass ,
        context,
        locationViewModel)
}
