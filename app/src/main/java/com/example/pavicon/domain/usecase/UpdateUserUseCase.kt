package com.example.pavicon.domain.usecase

import android.app.Activity
import android.util.Log
import com.example.pavicon.common.Constants
import com.example.pavicon.data.remote.dto.UpdateUserDataDto
import com.example.pavicon.data.remote.dto.UpdateUserDto
import com.example.pavicon.data.remote.dto.getUserDataDto.GetUserDataDto
import com.example.pavicon.data.repository.SharedPreferenceRepositoryImpl
import com.example.pavicon.data.repository.UserRepositoryImpl
import com.example.pavicon.domain.repository.SharedPreferenceRepository
import com.example.pavicon.domain.repository.UserRepository

class UpdateUserUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
    private val sharedPreferenceRepository:SharedPreferenceRepository = SharedPreferenceRepositoryImpl()

) {
    suspend fun updateUser(activity: Activity, updateUserDto: UpdateUserDto): UpdateUserDataDto {
        val token = sharedPreferenceRepository.getString(Constants.JWTOKEN, activity) ?: ""
        return try {
            userRepository.updateUserData(token, updateUserDto)
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("UpdateUserStatus", it) }
            UpdateUserDataDto(
                status = false,
                message = "An expected error occured",
            )
        }
    }

}