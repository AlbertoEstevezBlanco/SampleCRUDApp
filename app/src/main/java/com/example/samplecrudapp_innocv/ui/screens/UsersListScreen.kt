package com.example.samplecrudapp_innocv.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.samplecrudapp_innocv.model.User
import com.example.samplecrudapp_innocv.ui.components.NameFilterMenu
import com.example.samplecrudapp_innocv.ui.components.UserView
import com.example.samplecrudapp_innocv.ui.theme.Typography

@Composable
fun UsersListScreen(
    users: List<User> = emptyList(),
    nameFilter: ((User) -> Boolean)? = null,
    errorMsg: String? = null,
    isLoading: Boolean = false,
    showUserFormCallback: () -> Unit,
    updateUserCallback: (userId: Int?, newUsername: String, newBirthdate: String) -> Unit,
    deleteUserCallback: (User) -> Unit,
    clearNameFilterCallback: () -> Unit,
    filterByNameCallback: (username: String) -> Unit
) {
    val usersToShow = nameFilter?.let { users.filter(it) } ?: users
    val showDefineFiltersMenu = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(contentPadding = PaddingValues(16.dp)) {
                Text(text = "Users List")
                Spacer(modifier = Modifier.width(16.dp))
                if (showDefineFiltersMenu.value) {
                    NameFilterMenu(
                        displayMenu = showDefineFiltersMenu,
                        clearNameFilterCallback = clearNameFilterCallback,
                        filterByNameCallback = filterByNameCallback
                    )
                }
                else {
                    IconButton(
                        onClick = { showDefineFiltersMenu.value = true }
                    ) { Icon(imageVector = Icons.Filled.Search, contentDescription = "Show filter menu button") }
                }
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Create User") },
                onClick = { showUserFormCallback() },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            if (isLoading) CircularProgressIndicator(Modifier.padding(it))
            UsersListContent(
                modifier = Modifier.padding(it),
                users = usersToShow,
                errorMsg = errorMsg,
                updateUserCallback = updateUserCallback,
                deleteUserCallback = deleteUserCallback
            )

        }
    )
}

@Composable
fun UsersListContent(
    modifier: Modifier = Modifier,
    users: List<User> = emptyList(),
    errorMsg: String? = null,
    updateUserCallback: (userId: Int?, newUsername: String, newBirthdate: String) -> Unit,
    deleteUserCallback: (User) -> Unit
) {
    if (users.isNotEmpty()) UsersList(modifier, users, deleteUserCallback, updateUserCallback)
    else NoUsersView(errorMsg)
}

@Composable
fun UsersList(
    modifier: Modifier,
    users: List<User>,
    deleteUserCallback: (User) -> Unit,
    updateUserCallback: (userId: Int?, newUsername: String, newBirthdate: String) -> Unit
) {
    val userOnEditState: MutableState<User?> = remember { mutableStateOf(null) }
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(users) { user ->
            UserView(user, updateUserCallback, deleteUserCallback, userOnEditState)
        }
    }
}

@Composable
fun NoUsersView(errorMsg: String?) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = "Warning Icon")
        Text(
            text = errorMsg ?: "Something wrong happened when trying to get users from backend",
            style = Typography.caption
        )
    }

}