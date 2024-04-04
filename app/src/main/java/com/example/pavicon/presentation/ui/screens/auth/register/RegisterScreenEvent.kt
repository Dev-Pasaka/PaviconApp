package com.example.pavicon.presentation.ui.screens.auth.register

import android.app.Activity
import android.content.Context
import androidx.navigation.NavHostController


sealed class RegisterScreenEvent {
    data class  Register(val activity: Activity, val navController:NavHostController):RegisterScreenEvent()
}