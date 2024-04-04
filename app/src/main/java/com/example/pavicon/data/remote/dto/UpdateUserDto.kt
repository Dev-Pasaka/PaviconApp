package com.example.pavicon.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDto(
    @SerialName("email")
    val email: String?,
    @SerialName("firstName")
    val firstName: String?,
    @SerialName("gender")
    val gender: String?,
    @SerialName("age")
    val age:Int?,
    @SerialName("lastName")
    val lastName: String?,
    @SerialName("town")
    val town: String?
)
