
package com.example.samplecrudapp_innocv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.samplecrudapp_innocv.model.User
import com.example.samplecrudapp_innocv.mvi.base.BaseState
import com.example.samplecrudapp_innocv.mvi.base.MviIntent
import com.example.samplecrudapp_innocv.mvi.base.MviView
import com.example.samplecrudapp_innocv.mvi.intent.*
import com.example.samplecrudapp_innocv.repository.UsersRepository
import com.example.samplecrudapp_innocv.repository.UsersRepositoryBuilder
import com.example.samplecrudapp_innocv.ui.screens.UserFormScreen
import com.example.samplecrudapp_innocv.ui.screens.UsersListScreen
import com.example.samplecrudapp_innocv.ui.theme.SampleCRUDApp_INNOCVTheme
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity(), MviView<MviIntent, BaseState> {

    private val mainViewModel: MainViewModel by viewModels(
        factoryProducer = {
            MainViewModelFactory(
                usersRepository = UsersRepositoryBuilder.getInstance().create(UsersRepository::class.java)
            )
        }
    )


    override fun onIntent(intent: MviIntent) {
        mainViewModel.onIntent(intent)
    }

    @Composable
    override fun Render(state: BaseState) {
        if (state.showList)
            UsersListScreen(
                users = state.users ?: emptyList(),
                nameFilter = state.nameFilter,
                errorMsg = state.errorMsg,
                showUserFormCallback = { onIntent(ShowCreateUserFormIntent()) },
                updateUserCallback = { userId, newUsername, newBirthdate ->
                   onIntent(UpdateUserIntent(userId, newUsername, newBirthdate))
                },
                deleteUserCallback = { user: User ->
                    user.id?.let { onIntent(DeleteUserIntent(userId = it)) }
                },
                filterByNameCallback = { username: String -> onIntent(FilterByNameIntent(username))},
                clearNameFilterCallback = { onIntent(ClearFilterIntent) },
                isLoading = state.isLoading
        ) else UserFormScreen(
            createUser = { username, dateString ->
                onIntent(CreateUserIntent(username, dateString))
           },
            showUsersList = { onIntent(ShowUsersListIntent()) })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SampleCRUDApp_INNOCVTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Render(state = mainViewModel.state.collectAsState(Dispatchers.Default).value)
                }
            }
        }
    }
}
