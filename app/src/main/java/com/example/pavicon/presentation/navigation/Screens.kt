package com.example.pavicon.presentation.navigation


sealed class Screen(val route:String){
    data object SplashScreen: Screen("splash_screen")
    data object SignInScreen: Screen("signIn_screen")
    data object RegisterScreen: Screen("register_screen")
    data object ResetPassword: Screen("resetPassword_screen")
    data object HomeScreen: Screen("home_screen")
}