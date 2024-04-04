package com.example.pavicon.domain.usecase

import android.util.Log
import com.example.pavicon.common.Constants
import com.example.pavicon.common.Resource
import com.example.pavicon.data.remote.dto.RegisterUserDto
import com.example.pavicon.data.remote.dto.UserRegistrationDataDto
import com.example.pavicon.data.remote.dto.toUser
import com.example.pavicon.data.repository.UserRepositoryImpl
import com.example.pavicon.domain.repository.UserRepository
import io.ktor.serialization.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl()
) {
    fun registerUser(userRegistrationDataDto: UserRegistrationDataDto): Flow<Resource<RegisterUserDto>> = flow {
        try {

            emit(Resource.Loading(message = "Loading"))
            val result = userRepository.registerUser(userRegistrationDataDto)
            Log.e("response", result.toString())
            if (result.status) {
                emit(Resource.Success(data = result, message = result.message))
            }else{
                emit(Resource.Failed(data = result, message = result.message))
            }



        }
        catch (e:IOException){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            emit(Resource.Error( "Couldn't reach server check your internet connection"))
        }
        catch (e: JsonConvertException){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            emit(Resource.Error( "Wrong email or password"))
        }

        catch (e:Exception){
            e.localizedMessage?.let { Log.e("LoginStatus", it) }
            emit(Resource.Error( "An expected error occurred"))
        }
    }
}

suspend fun main(){
    RegisterUserUseCase().registerUser(
        UserRegistrationDataDto(
            "",
            "",
            "",
            "",
            "Male",
            23,
            "123456"
        )
    ).collect{
        println("${it.message} ${it.data}")
    }
}