package com.example.pavicon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserDto(
    val status:Boolean,
    val message:String
)