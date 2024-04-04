package com.example.pavicon.presentation.ui.screens.auth.register


data class RegisterUserState(
    val isLoading:Boolean = false,
    val error:String? = null,
    val isSuccessful:Boolean = false
)