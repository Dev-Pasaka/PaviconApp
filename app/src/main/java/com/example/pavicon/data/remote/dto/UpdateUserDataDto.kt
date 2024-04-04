package com.example.pavicon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserDataDto(
    val status:Boolean,
    val message:String
)
