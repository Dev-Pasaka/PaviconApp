package com.example.pavicon.data.remote.dto


import com.example.pavicon.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInDto(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Boolean,
    @SerialName("token")
    val token: String
)

fun SignInDto.toUser():User{
    return User(
        status = status,
        token = token,
        message = message
    )
}