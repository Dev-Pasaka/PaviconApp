package com.example.pavicon.domain.usecase

import android.app.Activity
import android.util.Log
import androidx.compose.ui.unit.Constraints
import com.example.pavicon.common.Constants
import com.example.pavicon.data.remote.dto.DeleteUserDto
import com.example.pavicon.data.remote.dto.getUserDataDto.GetUserDataDto
import com.example.pavicon.data.repository.SharedPreferenceRepositoryImpl
import com.example.pavicon.data.repository.UserRepositoryImpl
import com.example.pavicon.domain.repository.SharedPreferenceRepository
import com.example.pavicon.domain.repository.UserRepository

class DeleteUserUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun deleteUser(activity: Activity):DeleteUserDto {

        val userToken = sharedPreferenceRepository.getString(key = Constants.JWTOKEN, activity = activity) ?: ""
        Log.e("DeleteUserStatus", userToken)

        return try {
            val result = userRepository.deleteUser(userToken)
            Log.e("DeleteUserStatus", result.toString())
            result
        } catch (e:Exception){
            e.localizedMessage?.let { Log.e("DeleteUserStatus", it) }
            DeleteUserDto(
                status = false,
                message = "An expected error occurred",
            )
        }

    }
}