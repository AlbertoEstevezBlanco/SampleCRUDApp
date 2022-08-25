package com.example.samplecrudapp_innocv.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.samplecrudapp_innocv.ui.components.UserForm

@Composable
fun UserFormScreen(
    createUser: (username: String, dateString: String) -> Unit,
    showUsersList: () -> Unit,
) {
    val nameFieldState = remember { mutableStateOf("") }
    val dateFieldState = remember { mutableStateOf("") }
    val incorrectDateFormatState = remember { mutableStateOf(false)}


    Scaffold(
        topBar = {
             TopAppBar(contentPadding = PaddingValues(16.dp)) {
                 Text("Create User")
             }
        },
        floatingActionButton = {
           ExtendedFloatingActionButton(
               text = { Text("Users List") },
               onClick = { showUsersList() },
           )
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                UserForm(
                    nameFieldState = nameFieldState,
                    dateFieldState = dateFieldState,
                    incorrectDateFormatState = incorrectDateFormatState
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    enabled = !incorrectDateFormatState.value,
                    onClick = { createUser(nameFieldState.value, dateFieldState.value) }
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Create user button")
                    Spacer(Modifier.size(ButtonDefaults.IconSize))
                    Text("Create User")
                }
            }
        }
    )

}