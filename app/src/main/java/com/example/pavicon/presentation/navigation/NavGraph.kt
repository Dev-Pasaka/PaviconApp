package com.example.pavicon.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pavicon.presentation.ui.screens.auth.register.components.RegisterScreen
import com.example.pavicon.presentation.ui.screens.auth.resetPassword.ResetPasswordScreen
import com.example.pavicon.presentation.ui.screens.auth.signIn.SignInScreen
import com.example.pavicon.presentation.ui.screens.auth.splashScreen.SplashScreen
import com.example.pavicon.presentation.ui.screens.home.HomeScreen

@Composable
fun NavGraph(navController:NavHostController) {

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){

        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.SignInScreen.route){
            SignInScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route){
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.ResetPassword.route){
            ResetPasswordScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route){
            HomeScreen(navController = navController)
        }

    }

}