package com.example.pavicon.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pavicon.R
import com.example.pavicon.presentation.navigation.NavGraph
import com.example.pavicon.presentation.theme.PaviconTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaviconTheme {
                // A surface container using the 'background' color from the theme

                // Remember a SystemUiController
                val systemUiController = rememberSystemUiController()
                val background = Color.White
                SideEffect {
                    systemUiController.setSystemBarsColor(color = background)
                    systemUiController.setStatusBarColor(color = background)
                }
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

