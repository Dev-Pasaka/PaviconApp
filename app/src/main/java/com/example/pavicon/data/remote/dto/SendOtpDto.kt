package com.example.pavicon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
class SendOtpDto(
    val status:Boolean,
    val message:String
)