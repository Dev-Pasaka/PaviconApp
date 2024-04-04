package com.example.pavicon.presentation.ui.screens.auth.signIn

import com.example.pavicon.domain.model.User

data class SignInState(
    val isLoading:Boolean = false,
    val message:String = "",
    val isSuccessful:Boolean = false
)
