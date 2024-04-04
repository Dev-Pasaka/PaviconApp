package com.example.pavicon.domain.usecase

import android.util.Log
import com.example.pavicon.common.Resource
import com.example.pavicon.data.remote.dto.ResetPasswordDataDto
import com.example.pavicon.data.remote.dto.ResetPasswordDto
import com.example.pavicon.data.remote.dto.UserRegistrationDataDto
import com.example.pavicon.data.repository.UserRepositoryImpl
import com.example.pavicon.domain.repository.UserRepository
import io.ktor.serialization.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ResetPasswordUseCase(
    val userRepository: UserRepository = UserRepositoryImpl()
) {

    suspend fun resetPassword(resetPasswordDataDto: ResetPasswordDataDto): ResetPasswordDto {

        return try {

            userRepository.resetPassword(resetPasswordDataDto)



        } catch (e: IOException) {
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            ResetPasswordDto(status = false,e.localizedMessage ?: "Couldn't reach server check your internet connection")
        } catch (e: JsonConvertException) {
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            ResetPasswordDto(status = false,e.localizedMessage ?: "Wrong email or password")
        } catch (e: Exception) {
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            ResetPasswordDto(status = false,e.localizedMessage ?: "An expected error occurred")
        }

    }

}