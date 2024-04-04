package com.example.pavicon.domain.usecase

import android.app.Activity
import android.util.Log
import com.example.pavicon.common.Constants
import com.example.pavicon.common.Resource
import com.example.pavicon.data.remote.dto.UserCredentialsDto
import com.example.pavicon.data.remote.dto.toUser
import com.example.pavicon.data.repository.SharedPreferenceRepositoryImpl
import com.example.pavicon.data.repository.UserRepositoryImpl
import com.example.pavicon.domain.model.User
import com.example.pavicon.domain.repository.SharedPreferenceRepository
import com.example.pavicon.domain.repository.UserRepository
import io.ktor.serialization.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SignInUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
    private val sharedPreferenceRepository: SharedPreferenceRepository = SharedPreferenceRepositoryImpl()
) {
    suspend fun signIn(userCredentialsDto: UserCredentialsDto, activity:Activity): User {
        return try {

            val result = userRepository.signIn(userCredentialsDto).toUser()
            if (result.status){
                sharedPreferenceRepository.setString(key = Constants.JWTOKEN, value =result.token, activity = activity)
            }
            Log.e("SignInResult", result.toString())
            result

        }
        catch (e:IOException){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            User(status = false, token = "", message = "Couldn't reach server check your internet connection")
        }
        catch (e:JsonConvertException){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            User(status = false, token = "", message = "Wrong email or password")

        }
        catch (e:Exception){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            User(status = false, token = "", message = "An expected error occurred")
        }
    }
}