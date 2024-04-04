package com.example.pavicon.presentation.ui.screens.auth.splashScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.pavicon.presentation.navigation.Screen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()


    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ), label = ""
    )


    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        navController.navigate(Screen.SignInScreen.route){
            navController.popBackStack()
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = rememberAsyncImagePainter("https://media.licdn.com/dms/image/C4D03AQEnVWioCZ1yeg/profile-displayphoto-shrink_800_800/0/1651156401444?e=1717632000&v=beta&t=uu_4dGSg1zeZh-0KTJBKq-T4AyP31IVdriVBdynDrOA"),
                contentDescription = "Person",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(250.dp)
                    .clip(shape = CircleShape)
                    .border(4.dp, MaterialTheme.colorScheme.surfaceVariant, CircleShape)
            )
        }
    }

}