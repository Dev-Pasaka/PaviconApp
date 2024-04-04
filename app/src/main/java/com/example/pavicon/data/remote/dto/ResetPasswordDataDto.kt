package com.example.pavicon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordDataDto(
    val email:String,
    val newPassword:String,
    val otpCode:String
)