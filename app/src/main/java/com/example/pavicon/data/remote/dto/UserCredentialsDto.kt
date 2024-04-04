package com.example.pavicon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentialsDto(
    val email:String,
    val password:String
)