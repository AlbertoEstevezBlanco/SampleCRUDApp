package com.example.samplecrudapp_innocv.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.samplecrudapp_innocv.model.User
import com.example.samplecrudapp_innocv.ui.theme.Typography
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun UserView(
    user: User,
    updateUserCallback: (userId: Int?, newUsername: String, newBirthdate: String) -> Unit,
    deleteUserCallback: (User) -> Unit,
    userOnEditState: MutableState<User?>
) {
    val isBeingEdited = (user.id == userOnEditState.value?.id && userOnEditState.value != null)
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 3.dp,
        border = BorderStroke(1.dp, Color.LightGray)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            if (isBeingEdited) UserOnEditView(user, updateUserCallback)
            else UserInfoView(user)
            UserCardButtons(user, deleteUserCallback, userOnEditState, isBeingEdited)
        }

    }
}

@Composable
fun UserOnEditView (
    user: User,
    updateUserCallback: (userId: Int?, newUsername: String, newBirthdate: String) -> Unit
) {
    val nameFieldState = remember { mutableStateOf(user.name) }
    val dateFieldState = remember { mutableStateOf(user.birthdate.toFormattedBirthDateString()) }
    val incorrectDateFormatState = remember { mutableStateOf(false)}

    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserForm(
            nameFieldState = nameFieldState,
            dateFieldState = dateFieldState,
            incorrectDateFormatState = incorrectDateFormatState
        )
        IconButton(
            onClick = {
                updateUserCallback(user.id, nameFieldState.value, dateFieldState.value)
            },
            enabled = !incorrectDateFormatState.value
        ) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "SEND button")
        }
    }
}
@Composable
fun UserInfoView(
    user: User
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = user.name,
            style = Typography.h6
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = user.birthdate.toFormattedBirthDateString(),
            style = Typography.caption
        )
    }

}

@Composable
fun UserCardButtons(
    user: User,
    deleteUserCallback: (User) -> Unit,
    userOnEditState: MutableState<User?>,
    isBeingEdited: Boolean
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { userOnEditState.value = if (isBeingEdited) null else user }
        ) {
            if (isBeingEdited)
                Icon(imageVector = Icons.Filled.Clear, contentDescription = "Stop editting button")
            else
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit button")
        }
        IconButton(onClick = { deleteUserCallback(user) }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete button")
        }
    }
}

private fun Date.toFormattedBirthDateString() : String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(this)
}