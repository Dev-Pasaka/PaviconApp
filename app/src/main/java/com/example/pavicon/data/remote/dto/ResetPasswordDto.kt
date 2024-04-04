package com.example.pavicon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordDto(
    val status:Boolean,
    val message:String
)