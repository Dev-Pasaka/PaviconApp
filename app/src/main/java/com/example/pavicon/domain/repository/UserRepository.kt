package com.example.pavicon.domain.repository

import com.example.pavicon.data.remote.dto.*
import com.example.pavicon.data.remote.dto.getUserDataDto.GetUserDataDto

interface  UserRepository {

    suspend fun signIn(userCredentialsDto: UserCredentialsDto):SignInDto
    suspend fun registerUser(userRegistrationDataDto: UserRegistrationDataDto):RegisterUserDto
    suspend fun sendOtp(email:String):SendOtpDto
    suspend fun resetPassword(resetPasswordDataDto: ResetPasswordDataDto):ResetPasswordDto
    suspend fun getUserData(token:String):GetUserDataDto
    suspend fun updateUserData(token: String, updateUseDto: UpdateUserDto):UpdateUserDataDto
    suspend fun deleteUser(token: String):DeleteUserDto
}