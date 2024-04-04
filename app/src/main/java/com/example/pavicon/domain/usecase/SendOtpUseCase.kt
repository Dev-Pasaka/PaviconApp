package com.example.pavicon.domain.usecase

import android.content.Context
import android.util.Log
import androidx.navigation.NavHostController
import com.example.pavicon.common.Resource
import com.example.pavicon.data.remote.dto.SendOtpDto
import com.example.pavicon.data.repository.UserRepositoryImpl
import com.example.pavicon.domain.repository.UserRepository
import io.ktor.serialization.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SendOtpUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl()
) {
    suspend fun sendOtp(email:String): SendOtpDto {
        return try {
            userRepository.sendOtp(email = email)
        } catch (e: IOException) {
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            SendOtpDto(status = false, "Couldn't reach server check your internet connection")
        } catch (e: JsonConvertException) {
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            SendOtpDto(status = false, "Wrong email or password")
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            SendOtpDto(status = false, "An expected error occurred")
        }

    }
}