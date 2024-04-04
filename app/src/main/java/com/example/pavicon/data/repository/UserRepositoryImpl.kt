package com.example.pavicon.data.repository

import com.example.pavicon.data.remote.HttpClient
import com.example.pavicon.data.remote.dto.*
import com.example.pavicon.data.remote.dto.getUserDataDto.GetUserDataDto
import com.example.pavicon.domain.repository.UserRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val api: HttpClient = HttpClient
) : UserRepository {
    override suspend fun signIn(userCredentialsDto: UserCredentialsDto): SignInDto = withContext(Dispatchers.IO) {
        return@withContext api.client.post("${api.baseUrl}api/v1/signIn") {
            setBody(userCredentialsDto)
        }.body<SignInDto>()

    }

    override suspend fun registerUser(userRegistrationDataDto: UserRegistrationDataDto): RegisterUserDto = withContext(Dispatchers.IO) {
        return@withContext api.client.post("${api.baseUrl}api/v1/registerUser"){
            setBody(userRegistrationDataDto)
        }.body<RegisterUserDto>()
    }

    override suspend fun sendOtp(email: String): SendOtpDto = withContext(Dispatchers.IO) {
        return@withContext api.client.post("${api.baseUrl}api/v1/sendOtp?channel=email"){
            setBody(mapOf("emailTo" to email))
        }.body<SendOtpDto>()
    }

    override suspend fun resetPassword(resetPasswordDataDto: ResetPasswordDataDto): ResetPasswordDto  = withContext(Dispatchers.IO){
        return@withContext api.client.post("${api.baseUrl}api/v1/resetPassword"){
            setBody(resetPasswordDataDto)
        }.body<ResetPasswordDto>()
    }

    override suspend fun getUserData(token: String): GetUserDataDto = withContext(Dispatchers.IO) {
        return@withContext api.client.get("${api.baseUrl}api/v1/getUser"){
            header(HttpHeaders.Authorization, "Bearer $token")

        }.body<GetUserDataDto>()
    }

    override suspend fun updateUserData(token: String, updateUseDto: UpdateUserDto): UpdateUserDataDto = withContext(Dispatchers.IO) {
       return@withContext api.client.post("${api.baseUrl}api/v1/updateUser"){
           header(HttpHeaders.Authorization, "Bearer $token")

           setBody(
                UpdateUserDto(
                    firstName = if (updateUseDto.firstName?.isBlank() == true) null else updateUseDto.firstName,
                    lastName = if (updateUseDto.lastName?.isBlank() == true) null else updateUseDto.lastName,
                    email = if (updateUseDto.email?.isBlank() == true) null else updateUseDto.email,
                    gender = if (updateUseDto.gender?.isBlank() == true) null else updateUseDto.gender,
                    town = if (updateUseDto.town?.isBlank() == true) null else updateUseDto.town,
                    age =  updateUseDto.age,
                )
            )
        }.body<UpdateUserDataDto>()
    }

    override suspend fun deleteUser(token: String): DeleteUserDto = withContext(Dispatchers.IO) {

        return@withContext api.client.get("${api.baseUrl}api/v1/deleteUser"){
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body<DeleteUserDto>()
    }

}

suspend fun main(){
    println(
        UserRepositoryImpl().deleteUser(
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJqd3QtYXVkaWVuY2UiLCJpc3MiOiJodHRwczovL2p3dC1wcm92aWRlci1kb21haW4vIiwiZW1haWwiOiJkZXYucGFzYWthQGdtYWlsLmNvbSIsImV4cCI6MTcxMjQ4MDEzN30.mnK_F_o7bL2vSK2nQWQx7b_H2JHr259Cjt-KlSHZdeQ"
        )
    )
}

