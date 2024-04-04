package com.example.pavicon.presentation.ui.screens.auth.resetPassword

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.pavicon.common.Resource
import com.example.pavicon.data.remote.dto.ResetPasswordDataDto
import com.example.pavicon.domain.usecase.ResetPasswordUseCase
import com.example.pavicon.presentation.navigation.Screen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ResetPasswordViewModel(
    private val resetPasswordUseCase: ResetPasswordUseCase = ResetPasswordUseCase()
):ViewModel() {

    var resetPasswordState by mutableStateOf(ResetPasswordState())
        private set

    var newPassword by mutableStateOf("")
        private set
    var otpCode by mutableStateOf("")
        private set

    var emailReset by mutableStateOf("")
        private set

    var isPasswordResetting by mutableStateOf(false)
        private set

    fun updateEmailReset(text:String){ emailReset = text}
    fun updateNewPassword(text:String){ newPassword = text}
    fun updateOtpCode(text:String){ otpCode = text}


    fun resetPassword(resetPasswordDataDto:ResetPasswordDataDto,navController:NavHostController, context:Context){
        viewModelScope.launch {
            isPasswordResetting = true
            val result = resetPasswordUseCase.resetPassword(resetPasswordDataDto)
            resetPasswordState = ResetPasswordState(
                isSuccessful = result.status,
                message = result.message
            )
            if (result.status){
                isPasswordResetting = false
                Toast.makeText(context, "Password reset was successful", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.SignInScreen.route)
            }else{
                isPasswordResetting = false
                Toast.makeText(context, "Password reset was failed", Toast.LENGTH_SHORT).show()

            }
        }
    }
}