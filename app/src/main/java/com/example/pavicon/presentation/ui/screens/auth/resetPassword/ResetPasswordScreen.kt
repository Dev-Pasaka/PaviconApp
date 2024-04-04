package com.example.pavicon.presentation.ui.screens.auth.resetPassword

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pavicon.data.remote.dto.ResetPasswordDataDto
import com.example.pavicon.presentation.navigation.Screen
import com.example.pavicon.presentation.ui.screens.auth.register.RegisterScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ResetPasswordScreen(navController: NavHostController) {
    val resetPasswordViewModel = viewModel<ResetPasswordViewModel>()
    val context = LocalContext.current




    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Reset Password",
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Enter email",
                    color = Color.DarkGray,
                )
            },
            value = resetPasswordViewModel.emailReset,
            onValueChange = {
                resetPasswordViewModel.updateEmailReset(text = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier.fillMaxWidth(0.75f),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "New Password",
                    color = Color.DarkGray,
                )
            },
            value = resetPasswordViewModel.newPassword,
            onValueChange = {
                resetPasswordViewModel.updateNewPassword(text = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier.fillMaxWidth(0.75f),
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Enter Otp Code",
                    color = Color.DarkGray,
                )
            },
            value = resetPasswordViewModel.otpCode,
            onValueChange = {
                resetPasswordViewModel.updateOtpCode(text = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier.fillMaxWidth(0.75f),
        )

        Spacer(modifier = Modifier.height(16.dp))



        when (resetPasswordViewModel.isPasswordResetting) {
            true -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Butt
                )
            }

            else -> {
                Button(
                    onClick = {
                        resetPasswordViewModel.resetPassword(
                            resetPasswordDataDto = ResetPasswordDataDto(
                                email = resetPasswordViewModel.emailReset,
                                newPassword = resetPasswordViewModel.newPassword,
                                otpCode = resetPasswordViewModel.otpCode
                            ),
                            navController = navController,
                            context = context
                        )




                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth(0.75f),

                    ) {
                    Text(
                        text = "Reset Password",
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))


        TextButton(
            onClick = {
                navController.navigate(Screen.SignInScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {

            Text(
                text = "Sign in",
            )

        }


    }


}