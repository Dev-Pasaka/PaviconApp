package com.example.pavicon.presentation.ui.screens.auth.register.components

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pavicon.presentation.navigation.Screen
import com.example.pavicon.presentation.ui.screens.auth.register.RegisterScreenEvent
import com.example.pavicon.presentation.ui.screens.auth.register.RegisterScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    navController: NavHostController,
) {
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val offsetX by animateFloatAsState(
        targetValue = if (startAnimation) 0f else with(LocalDensity.current) { -50.dp.toPx() },
        animationSpec = tween(durationMillis = 1000), label = ""
    )
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity
    val scrollState = rememberScrollState()
    val registerScreenViewModel = viewModel<RegisterScreenViewModel>()
    var isGenderDropDownOpen by rememberSaveable {
        mutableStateOf(false)
    }


    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var isConfirmPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        scope.launch {
            startAnimation = true
            delay(1000)
        }
    }



    Scaffold(
        snackbarHost = {

            if (registerScreenViewModel.registerUserState.isSuccessful) {
                Snackbar(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ) {
                    Text("Registration successful")
                }
            } else if (registerScreenViewModel.registerUserState.error?.isNotEmpty() == true) {
                Snackbar(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                ) {
                    Text("Registration failed")
                }
            }

        }
    ) {

        Surface(
            modifier = Modifier
            //.offset(x = offsetX.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(state = scrollState)
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Go back")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Register Account",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))


                OutlinedTextField(
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "First Name",
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    value = registerScreenViewModel.firstName,
                    onValueChange = {
                        registerScreenViewModel.updateFirstName(it)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.fillMaxWidth(),


                    )

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Last Name",
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    value = registerScreenViewModel.lastName,
                    onValueChange = {
                        registerScreenViewModel.updateLastName(it)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.fillMaxWidth(),


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
                    value = registerScreenViewModel.email,
                    onValueChange = {
                        registerScreenViewModel.updateEmail(it)

                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.fillMaxWidth(),


                    )

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Town",
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    value = registerScreenViewModel.town,
                    onValueChange = {
                        registerScreenViewModel.updateTown(it)

                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.fillMaxWidth(),


                    )


                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    readOnly = true,
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Gender",
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    value = registerScreenViewModel.gender,
                    onValueChange = {
                        ""
                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = { isGenderDropDownOpen = !isGenderDropDownOpen }
                        ) {
                            Icon(imageVector = Icons.Outlined.KeyboardArrowDown, contentDescription = "Choose Gender")

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
                    modifier = Modifier.fillMaxWidth(),


                    )

                if (isGenderDropDownOpen) {
                    DropdownMenu(
                        expanded = true, onDismissRequest = { isGenderDropDownOpen = !isGenderDropDownOpen },
                        offset = DpOffset(x = 250.dp, y = 500.dp)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Male")
                            },
                            onClick = {
                                registerScreenViewModel.selectGender("Male")
                                isGenderDropDownOpen = !isGenderDropDownOpen
                            }

                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Female")
                            },
                            onClick = {
                                registerScreenViewModel.selectGender("Female")
                                isGenderDropDownOpen = !isGenderDropDownOpen

                            })
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Age",
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    value = registerScreenViewModel.age,
                    onValueChange = {
                        registerScreenViewModel.updateAge(it)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = MaterialTheme.colorScheme.onSurface,
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.fillMaxWidth(),


                    )

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Password",
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    value = registerScreenViewModel.password,
                    onValueChange = {
                        registerScreenViewModel.updatePassword(it)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
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
                    modifier = Modifier.fillMaxWidth(),


                    )

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Confirm Password",
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    },
                    value = registerScreenViewModel.confirmPassword,
                    onValueChange = {
                        registerScreenViewModel.updateConfirmPassword(it)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                    ),
                    visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else
                        PasswordVisualTransformation(),
                    trailingIcon = {
                        when (isConfirmPasswordVisible) {
                            true -> IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
                                Icon(imageVector = Icons.Outlined.VisibilityOff, contentDescription = "Show password")
                            }

                            else -> {
                                IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
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
                    modifier = Modifier.fillMaxWidth(),


                    )

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val context = LocalContext.current

                    when (registerScreenViewModel.registerUserState.isLoading) {
                        true -> {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary,
                                strokeWidth = 3.dp,
                                strokeCap = StrokeCap.Butt
                            )
                        }

                        else -> {
                            Button(
                                enabled = true,
                                onClick = {
                                    registerScreenViewModel.even(
                                        event = RegisterScreenEvent.Register(
                                            activity = activity,
                                            navController = navController
                                        )
                                    )

                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier.fillMaxWidth(),

                                ) {
                                Text(
                                    text = "Register",
                                )
                            }
                        }
                    }


                }

            }
        }
    }

}