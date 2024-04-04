package com.example.pavicon.presentation.ui.screens.auth.signIn.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.pavicon.data.remote.dto.ResetPasswordDataDto
import com.example.pavicon.presentation.navigation.Screen
import com.example.pavicon.presentation.ui.screens.auth.signIn.SignInEvent
import com.example.pavicon.presentation.ui.screens.auth.signIn.SignInScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignInScreenMiddleSection(
    navController: NavHostController,
    signInScreenViewModel: SignInScreenViewModel,
    actionSignIn: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {


        Image(
            painter = rememberAsyncImagePainter("https://media.licdn.com/dms/image/C4D03AQEnVWioCZ1yeg/profile-displayphoto-shrink_800_800/0/1651156401444?e=1717632000&v=beta&t=uu_4dGSg1zeZh-0KTJBKq-T4AyP31IVdriVBdynDrOA"),
            contentDescription = "Person",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(shape = CircleShape)
                .border(4.dp, MaterialTheme.colorScheme.surfaceVariant, CircleShape)
        )
        Spacer(modifier = Modifier.height(4.dp))

        when (signInScreenViewModel.isSignInSelected) {
            "Login" -> Login(
                navController = navController,
                signInScreenViewModel = signInScreenViewModel,
                actionSignIn
            )

            "ForgotPassword" -> ForgotPassword(
                signInScreenViewModel = signInScreenViewModel,
                navController = navController
            )
        }


    }
}


@Composable
fun Login(navController: NavHostController, signInScreenViewModel: SignInScreenViewModel, actionSignIn: () -> Unit) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val offsetX by animateFloatAsState(
        targetValue = if (startAnimation) 0f else with(LocalDensity.current) { -50.dp.toPx() },
        animationSpec = tween(durationMillis = 1000), label = ""
    )
    val scope = rememberCoroutineScope()

    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = true) {
        scope.launch {
            startAnimation = true
            delay(1000)
        }
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = offsetX.dp)
    ) {
        Text(
            text = "SignIn",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            singleLine = true,
            placeholder = {
                Text(
                    text = "Email",
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
            value = signInScreenViewModel.email,
            onValueChange = {
                signInScreenViewModel.updateEmail(text = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = Color.DarkGray,
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier.fillMaxWidth(0.75f),


            )

        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            singleLine = true,
            placeholder = {
                Text(
                    text = "Password",
                    color = Color.DarkGray,
                )
            },
            value = signInScreenViewModel.password,
            onValueChange = {
                signInScreenViewModel.updatePassword(text = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = Done, keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyBoardController?.hide()
                    actionSignIn()
                }
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else
                PasswordVisualTransformation(),
            trailingIcon = {
                when (isPasswordVisible) {
                    true -> IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(imageVector = Icons.Outlined.VisibilityOff, contentDescription = "Show password")
                    }

                    else -> {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(imageVector = Icons.Outlined.Visibility, contentDescription = "Hide password")
                        }
                    }
                }
            },
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


        when (signInScreenViewModel.signInState.isLoading) {
            true -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Butt
                )
            }

            else -> {
                Button(
                    enabled = signInScreenViewModel.email.isNotBlank() && signInScreenViewModel.password.isNotBlank(),
                    onClick = actionSignIn,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth(0.75f),

                    ) {
                    Text(
                        text = "SignIn",
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { signInScreenViewModel.updateLoginState(newState = "ForgotPassword") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.secondary
            )

        ) {
            Text(
                text = "Forgot Password?",
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(0.75f),


            ) {
            Text(
                text = "Don't have an account?",
                color = MaterialTheme.colorScheme.onSurface
            )

            TextButton(
                onClick = { navController.navigate(Screen.RegisterScreen.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.secondary
                )

            ) {
                Text(
                    text = "Register",
                )
            }
        }


    }

}

@Composable
fun ForgotPassword(signInScreenViewModel: SignInScreenViewModel, navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val offsetX by animateFloatAsState(
        targetValue = if (startAnimation) 0f else with(LocalDensity.current) { -50.dp.toPx() },
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(key1 = true) {
        scope.launch {
            startAnimation = true
            delay(1000)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = offsetX.dp)
    ) {
        Text(
            text = "Forgot Password",
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            placeholder = {
                Text(
                    text = "Enter Email",
                    color = Color.DarkGray,
                )
            },
            value = signInScreenViewModel.emailReset,
            onValueChange = {
                signInScreenViewModel.updateEmailReset(emailString = it)
            },
            keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
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

        Spacer(modifier = Modifier.height(8.dp))


        when (signInScreenViewModel.sendOtpState.isLoading) {
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
                        signInScreenViewModel.event(
                            event = SignInEvent.SendOtp(
                                email = signInScreenViewModel.emailReset,
                                context = context,
                                navController = navController
                            )
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
                        text = "Send Otp",
                    )
                }
            }
        }


    }
    Spacer(modifier = Modifier.height(16.dp))

    TextButton(
        onClick = {
            signInScreenViewModel.updateLoginState(newState = "Login")
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.secondary
        )
    ) {

        Text(
            text = "Back to login",
        )

    }

}


