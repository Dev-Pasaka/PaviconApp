package com.example.pavicon.domain.usecase

import android.app.Activity
import android.util.Log
import com.example.pavicon.common.Constants
import com.example.pavicon.data.remote.dto.getUserDataDto.GetUserDataDto
import com.example.pavicon.data.repository.SharedPreferenceRepositoryImpl
import com.example.pavicon.data.repository.UserRepositoryImpl
import com.example.pavicon.domain.repository.SharedPreferenceRepository
import com.example.pavicon.domain.repository.UserRepository

class GetUserDataUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
){
    suspend  fun getUserData(activity: Activity):GetUserDataDto{
        val token = sharedPreferenceRepository.getString(key = Constants.JWTOKEN, activity) ?: ""

        return try{
            val result = userRepository.getUserData(token = token)
            Log.e("GetUserResult", result.toString())
            result
        }
        catch (e:Exception){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            GetUserDataDto(
                status = false,
                message = "An expected error occured",
                user = null
            )
        }
    }

}
