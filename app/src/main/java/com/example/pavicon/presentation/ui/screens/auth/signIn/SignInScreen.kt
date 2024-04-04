package com.example.pavicon.presentation.ui.screens.auth.signIn

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pavicon.presentation.ui.screens.auth.signIn.components.SignInScreenMiddleSection


@Composable
fun SignInScreen(navController:NavHostController){
    val signInScreenViewModel = viewModel<SignInScreenViewModel>()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            SignInScreenMiddleSection(
                navController = navController,
                signInScreenViewModel = signInScreenViewModel,
                actionSignIn = {
                    signInScreenViewModel.event(
                        event = SignInEvent.SignIn(activity = activity, context = context, navController = navController)
                    )
                }
            )
        }
    }



}