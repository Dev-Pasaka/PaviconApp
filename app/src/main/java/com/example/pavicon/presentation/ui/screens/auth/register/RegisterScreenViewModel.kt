package com.example.pavicon.presentation.ui.screens.auth.register

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pavicon.common.Resource
import com.example.pavicon.data.remote.dto.UserRegistrationDataDto
import com.example.pavicon.domain.usecase.RegisterUserUseCase
import com.example.pavicon.domain.usecase.ValidateRegisterFormUseCase
import com.example.pavicon.presentation.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RegisterScreenViewModel(
    private val formValidation:ValidateRegisterFormUseCase = ValidateRegisterFormUseCase(),
    private val registerUserUseCase: RegisterUserUseCase = RegisterUserUseCase()
):ViewModel() {

    var registerUserState by mutableStateOf(RegisterUserState())
        private set

    var firstName  by mutableStateOf("")
        private set
    var lastName  by mutableStateOf("")
        private set
    var email  by mutableStateOf("")
        private set
    var town  by mutableStateOf("")
        private set
    var gender  by mutableStateOf("")
        private set
    var age  by mutableStateOf("")
        private set
    var password  by mutableStateOf("")
        private set
    var confirmPassword  by mutableStateOf("")
        private set
    var isRegistrationSuccessful by mutableStateOf(false)
        private set

    fun updateFirstName(text:String){firstName = text}
    fun updateLastName(text:String){lastName = text}
    fun updateEmail(text:String){email = text}
    fun updateTown(text:String){town = text}
    fun selectGender(text:String){gender = text}
    fun updateAge(text:String){age = text}
    fun updatePassword(text:String){password = text}
    fun updateConfirmPassword(text:String){confirmPassword = text}

    fun even(event: RegisterScreenEvent){
        when(event){
            is RegisterScreenEvent.Register ->{
                val result = formValidation.validateForm(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    town = town,
                    gender = gender,
                    age = age.toIntOrNull() ?: 0,
                    password = password,
                    confirmPassword = confirmPassword
                )
                when(result.isSuccess){
                    true ->{
                         registerUserUseCase.registerUser(
                            userRegistrationDataDto = UserRegistrationDataDto(
                                firstName = firstName,
                                lastName = lastName,
                                email = email,
                                town = town,
                                gender = gender,
                                age = age.toIntOrNull() ?: 0,
                                password = password
                            )
                        ).onEach {registrationResult ->
                              when(registrationResult){
                               is  Resource.Loading ->{
                                    registerUserState = RegisterUserState(
                                        isLoading = true,
                                        isSuccessful = false,
                                        error = null
                                    )
                                }
                                 is Resource.Success ->{
                                     registerUserState = RegisterUserState(
                                         isLoading =false,
                                         isSuccessful = true,
                                         error = null
                                     )
                                     delay(1000)
                                     event.navController.navigate(Screen.SignInScreen.route)

                                 }
                                 is Resource.Error ->{
                                     registerUserState = RegisterUserState(
                                         isLoading = false,
                                         isSuccessful = false,
                                         error = registrationResult.message
                                     )
                                 }
                                 is Resource.Failed ->{
                                     registerUserState = RegisterUserState(
                                         isLoading = false,
                                         isSuccessful = false,
                                         error = registrationResult.message
                                     )
                                 }
                            }
                        }.launchIn(viewModelScope)



                    }else ->{
                        Toast.makeText(event.activity, result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}