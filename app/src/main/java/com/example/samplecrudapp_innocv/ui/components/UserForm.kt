package com.example.samplecrudapp_innocv.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun UserForm(
    nameFieldState: MutableState<String>,
    dateFieldState: MutableState<String>,
    incorrectDateFormatState: MutableState<Boolean>
) {
    UsernameField(fieldState = nameFieldState)
    Spacer(Modifier.height(16.dp).width(4.dp))
    BirthDateField(fieldState = dateFieldState, incorrectFormat = incorrectDateFormatState)
}



@Composable
fun UsernameField(
    fieldState: MutableState<String>,
) {
    TextField(
        value = fieldState.value,
        onValueChange = { fieldState.value = it },
        label = { Text("Username") }
    )
}


@Composable
fun BirthDateField(
    fieldState: MutableState<String>,
    incorrectFormat: MutableState<Boolean>
) {
    TextField(
        value = fieldState.value,
        onValueChange = {
            fieldState.value = it
            incorrectFormat.value = !fieldState.value.isValidBirthdate()
        },
        label = { Text("Birth date") },
        placeholder = { Text("dd/MM/yyyy") },
        isError = incorrectFormat.value
    )
}

private fun String.isValidBirthdate(): Boolean {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return try {
        formatter.parse(this)
        true
    } catch (e: Exception) {
        false
    }
}