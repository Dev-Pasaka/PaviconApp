package com.example.pavicon.presentation.ui.screens.auth.signIn

import android.app.Activity
import android.content.Context
import androidx.navigation.NavHostController
import com.example.pavicon.data.remote.dto.ResetPasswordDataDto

sealed class SignInEvent {
    data class  SignIn(val activity:Activity, val navController: NavHostController, val context: Context):SignInEvent()
    data class SendOtp(val email:String, val context: Context, val navController: NavHostController):SignInEvent()
}