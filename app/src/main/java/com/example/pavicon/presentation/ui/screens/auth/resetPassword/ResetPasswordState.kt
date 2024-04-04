package com.example.pavicon.presentation.ui.screens.auth.resetPassword

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.example.pavicon.domain.model.User

data class ResetPasswordState(
    val isSuccessful:Boolean = false,
    val message: String = ""
)