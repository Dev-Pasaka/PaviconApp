package com.example.pavicon.domain.usecase

import android.util.Patterns
import com.example.pavicon.domain.model.RegisterFormValidation

class ValidateRegisterFormUseCase {
    fun validateForm(
        firstName:String,
        lastName:String,
        email:String,
        town:String,
        gender:String,
        age:Int,
        password:String,
        confirmPassword:String
    ):RegisterFormValidation{
        if(firstName.isBlank() || firstName.length < 3)
            return RegisterFormValidation(isSuccess = false, message = "Name should contain atleast 3 characters")
        if (lastName.isBlank() || lastName.length < 3)
            return RegisterFormValidation(isSuccess = false, message = "Name should contain atleast 3 characters")
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return RegisterFormValidation(isSuccess = false, message = "Email is not valid")
        if (town.isBlank())
            return RegisterFormValidation(isSuccess = false, message = "Town can not be blank")
        if (gender.isBlank())
            return RegisterFormValidation(isSuccess = false, message = "Gender can not be blank")
        if (age < 16)
            return RegisterFormValidation(isSuccess = false, message = "Minimum age required is 16 years")
        if ( password.length < 6)
            return RegisterFormValidation(isSuccess = false, message = "Password can not be less than 6 characters")
        if (password != confirmPassword)
            return RegisterFormValidation(isSuccess = false, message = "Password don't match")

        return RegisterFormValidation(isSuccess = true, message = "Form validation successful")




    }
}