package com.example.pavicon.presentation.ui.screens.home

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val activity = LocalContext.current as Activity
    val homeScreenViewModel = viewModel<HomeScreenViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeScreenViewModel(
                    activity = activity
                ) as T
            }
        }
    )


    var isDropDownOpen by rememberSaveable {
        mutableStateOf(false)
    }

    if (homeScreenViewModel.isRefreshing) {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 3.dp,
                strokeCap = StrokeCap.Butt
            )
        }
    }
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxSize()
    ) {

    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            IconButton(
                onClick = {
                    homeScreenViewModel.refreshUserData()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Refresh, contentDescription = "Refresh",
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Delete",
                    color = MaterialTheme.colorScheme.onSurface
                )

                IconButton(
                    onClick = {
                              homeScreenViewModel.deleteUser(navController = navController)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete, contentDescription = "Delete Account",
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)

            ) {
                Text(
                    text = "User data",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {


            //First Name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(100.dp)

                ) {
                    Text(
                        text = "First Name",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }



                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = "First Name",
                            color = Color.DarkGray,
                        )
                    },
                    value = homeScreenViewModel.firstName,
                    onValueChange = {
                        homeScreenViewModel.updateFirstName(it)
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
                        cursorColor = MaterialTheme.colorScheme.primaryContainer,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )


            }

            //Last Name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(100.dp)


                ) {
                    Text(
                        text = "Last Name",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }

                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = "Last Name",
                            color = Color.DarkGray,
                        )
                    },
                    value = homeScreenViewModel.lastName,
                    onValueChange = {
                        homeScreenViewModel.updateLastName(it)
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
                        cursorColor = MaterialTheme.colorScheme.primaryContainer,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )


            }

            //Email
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(100.dp)


                ) {
                    Text(
                        text = "Email",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }


                OutlinedTextField(
                    readOnly = true,
                    placeholder = {
                        Text(
                            text = "Email",
                            color = Color.DarkGray,
                        )
                    },
                    value = homeScreenViewModel.email,
                    onValueChange = {
                        homeScreenViewModel.updateEmail(it)
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
                        cursorColor = MaterialTheme.colorScheme.primaryContainer,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )


            }


            //Age
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(100.dp)


                ) {
                    Text(
                        text = "Age",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }

                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = "Age",
                            color = Color.DarkGray,
                        )
                    },
                    value = homeScreenViewModel.age,
                    onValueChange = {
                        homeScreenViewModel.updateAge(it)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = MaterialTheme.colorScheme.primaryContainer,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )


            }

            //Town
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(100.dp)


                ) {
                    Text(
                        text = "Town",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }


                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = "Town",
                            color = Color.DarkGray,
                        )
                    },
                    value = homeScreenViewModel.town,
                    onValueChange = {
                        homeScreenViewModel.updateTown(it)
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
                        cursorColor = MaterialTheme.colorScheme.primaryContainer,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )


            }

            //Gender
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .width(100.dp)

                ) {
                    Text(
                        text = "First Name",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }

                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = "Select Gender",
                            color = Color.DarkGray,
                        )
                    },
                    readOnly = true,
                    value = homeScreenViewModel.selectedGender,
                    onValueChange = {
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Text),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = { isDropDownOpen = !isDropDownOpen }
                        ) {
                            Icon(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "Select gender")
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.DarkGray,
                        unfocusedTextColor = Color.DarkGray,
                        cursorColor = MaterialTheme.colorScheme.primaryContainer,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )

                if (isDropDownOpen) {
                    DropdownMenu(
                        expanded = true,
                        onDismissRequest = { isDropDownOpen = !isDropDownOpen },
                        offset = DpOffset(x = 230.dp,  y = 0.dp)
                        ) {
                        DropdownMenuItem(
                            text = { Text(text = "Male") },
                            onClick = {
                                homeScreenViewModel.selectGender("Male")
                                isDropDownOpen = !isDropDownOpen
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Female") },
                            onClick = {
                                homeScreenViewModel.selectGender("Female")
                                isDropDownOpen = !isDropDownOpen
                            })

                    }
                }


            }


        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                      homeScreenViewModel.updateUser()
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update")
        }


    }

}