package com.example.pavicon.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRegistrationDataDto(
    val firstName:String,
    val lastName:String,
    val email:String,
    val town:String,
    val gender:String,
    val age:Int,
    val password:String,

)