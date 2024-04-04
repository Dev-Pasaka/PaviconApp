package com.example.pavicon.data.remote.dto.getUserDataDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserDataDto(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Boolean,
    @SerialName("user")
    val user: User?
)