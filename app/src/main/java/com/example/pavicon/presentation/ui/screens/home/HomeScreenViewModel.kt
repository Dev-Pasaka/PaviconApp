package com.example.pavicon.presentation.ui.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.pavicon.data.remote.dto.UpdateUserDto
import com.example.pavicon.domain.usecase.DeleteUserUseCase
import com.example.pavicon.domain.usecase.GetUserDataUseCase
import com.example.pavicon.domain.usecase.UpdateUserUseCase
import com.example.pavicon.presentation.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenViewModel @SuppressLint("StaticFieldLeak") constructor(
    private val getUserDataUseCase: GetUserDataUseCase = GetUserDataUseCase(),
    private val updateUserUseCase: UpdateUserUseCase = UpdateUserUseCase(),
    private val deleteUserUseCase: DeleteUserUseCase = DeleteUserUseCase(),
    private val activity: Activity
):ViewModel() {
    var firstName by mutableStateOf("")
        private set

    var lastName by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set
    var age by mutableStateOf("")
        private set
    var town by mutableStateOf("")
        private set
    var selectedGender by mutableStateOf("")
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    fun updateFirstName(text:String){firstName = text}
    fun updateLastName(text:String){lastName = text}
    fun updateEmail(text:String){email = text}
    fun updateAge(text:String){age = text}
    fun updateTown(text:String){town = text}
    fun selectGender(text:String){selectedGender = text}

    private suspend fun getUserData(){
       val result =  getUserDataUseCase.getUserData(activity = activity)
        firstName = result.user?.firstName ?: ""
        lastName = result.user?.lastName ?: ""
        email = result.user?.email ?: ""
        selectedGender = result.user?.gender ?: ""
        town = result.user?.town ?: ""
        age = result.user?.age.toString()
    }

    fun refreshUserData(){
        viewModelScope.launch {
            isRefreshing = true
            getUserData()
            isRefreshing = false
        }
    }

    fun updateUser(){
        viewModelScope.launch {
            isRefreshing = true
            updateUserUseCase.updateUser(
                activity =activity,
                updateUserDto = UpdateUserDto(
                    email = email,
                    firstName = firstName,
                    gender = selectedGender,
                    age = age.toInt(),
                    lastName = lastName,
                    town = town
                )
            )
            refreshUserData()
            isRefreshing = false
        }
    }

    fun deleteUser(navController:NavHostController){
        viewModelScope.launch {
            isRefreshing = true

            val result = deleteUserUseCase.deleteUser(activity)
            if(result.status){
                isRefreshing = false
                Toast.makeText(activity, result.message, Toast.LENGTH_LONG).show()
                delay(1000)
                navController.navigate(Screen.SignInScreen.route){
                    navController.popBackStack()
                }
            }
            isRefreshing = false


        }
    }

    init {
        viewModelScope.launch {
            getUserData()
        }
    }

}