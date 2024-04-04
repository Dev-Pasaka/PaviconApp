package com.example.pavicon.presentation.ui.screens.auth.signIn

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pavicon.common.Resource
import com.example.pavicon.data.remote.dto.UserCredentialsDto
import com.example.pavicon.domain.usecase.ResetPasswordUseCase
import com.example.pavicon.domain.usecase.SendOtpUseCase
import com.example.pavicon.domain.usecase.SignInUseCase
import com.example.pavicon.presentation.navigation.Screen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SignInScreenViewModel(
    private val signInUseCase: SignInUseCase = SignInUseCase(),
    private val sendOtpUseCase: SendOtpUseCase = SendOtpUseCase(),
):ViewModel() {

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var isSignInSelected by mutableStateOf("Login")
        private set

    var emailReset by mutableStateOf("")
        private set

    var signInState by mutableStateOf(SignInState())
        private set
    var sendOtpState by mutableStateOf(SendOtpState())
        private set


    var newPassword by mutableStateOf("")
        private set
    var otpCode by mutableStateOf("")
        private set





    fun updateEmail(text:String){ email = text}

    fun updatePassword(text:String){ password = text}
    fun updateEmailReset(emailString: String) { emailReset = emailString }
    fun updateLoginState(newState: String) { isSignInSelected = newState }


    fun event(event: SignInEvent){
        when(event){
            is SignInEvent.SignIn ->{
                viewModelScope.launch {
                    signInState = SignInState(isLoading = true, message = "Signing ...", false)
                    val result = signInUseCase.signIn(
                        userCredentialsDto = UserCredentialsDto(
                            email = email, password = password
                        ),
                        activity = event.activity
                    )
                    if (result.status){
                        Toast.makeText(event.context, result.message, Toast.LENGTH_LONG).show()
                        signInState = SignInState(isLoading = false, message = "Sign in successful", true)
                        event.navController.navigate(Screen.HomeScreen.route){
                            event.navController.popBackStack()
                        }
                    }else{
                        Toast.makeText(event.context, result.message, Toast.LENGTH_LONG).show()
                        signInState = SignInState(isLoading = false, message = "Sign in failed", false)
                    }
                }


                  /*  .onEach { result ->
                    signInState = when(result){
                        is Resource.Loading ->{
                            Log.e("result", result.message.toString())
                            SignInState(isLoading = true, user = result.data)
                        }
                        is Resource.Success ->{
                            Log.e("result", result.message.toString())
                            SignInState(isLoading = false, user = result.data)
                        }
                        is Resource.Error ->{
                            Log.e("result", result.message.toString())
                            SignInState(isLoading = false, user = result.data, error = result.message)
                        }
                        is Resource.Failed ->{
                            Log.e("result", result.message.toString())
                            SignInState(isLoading = false, user = result.data, error = result.message)
                        }
                    }

                }.launchIn(viewModelScope)*/
            }

            is SignInEvent.SendOtp ->{
                viewModelScope.launch {
                    sendOtpState = SendOtpState(isLoading = true)
                    val result = sendOtpUseCase.sendOtp(event.email)
                    if (result.status){
                        Toast.makeText(event.context, result.message, Toast.LENGTH_LONG).show()
                        sendOtpState = SendOtpState(isSuccessful = true, message = result.message, isLoading = false)
                        event.navController.navigate(Screen.ResetPassword.route)
                    }else{
                        Toast.makeText(event.context, result.message, Toast.LENGTH_LONG).show()
                        sendOtpState = SendOtpState(isSuccessful = false, message = result.message, isLoading = false)

                    }
                }
            }

        }
    }


}