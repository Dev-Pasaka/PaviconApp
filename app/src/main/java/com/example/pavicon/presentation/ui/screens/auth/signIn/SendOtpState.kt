package com.example.pavicon.presentation.ui.screens.auth.signIn

import com.example.pavicon.domain.model.User

data class SendOtpState(
    val isSuccessful:Boolean = false,
    val isLoading:Boolean = false,
    val message:String = ""
)