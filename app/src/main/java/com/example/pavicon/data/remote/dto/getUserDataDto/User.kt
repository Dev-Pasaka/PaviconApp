package com.example.pavicon.data.remote.dto.getUserDataDto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("email")
    val email: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("age")
    val age:Int,
    @SerialName("id")
    val id: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("town")
    val town: String
)