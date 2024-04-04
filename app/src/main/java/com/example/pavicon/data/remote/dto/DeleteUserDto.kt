package com.example.pavicon.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteUserDto(
    val status:Boolean,
    val message:String
)