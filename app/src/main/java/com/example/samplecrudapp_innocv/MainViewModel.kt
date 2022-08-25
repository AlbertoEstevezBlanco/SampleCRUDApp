package com.example.samplecrudapp_innocv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplecrudapp_innocv.mvi.action.*
import com.example.samplecrudapp_innocv.mvi.base.*
import com.example.samplecrudapp_innocv.mvi.result.ClearFilterResult
import com.example.samplecrudapp_innocv.mvi.result.LoadingUsersResult
import com.example.samplecrudapp_innocv.mvi.result.ShowCreateUserFormResult
import com.example.samplecrudapp_innocv.mvi.result.UnknownActionReceivedResult
import com.example.samplecrudapp_innocv.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {
    private val _state: MutableStateFlow<BaseState> =
        MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _intent: MutableSharedFlow<MviIntent> = MutableSharedFlow()
    private val intentFlow = _intent.asSharedFlow()

    private val _result: MutableSharedFlow<MviResult> = MutableSharedFlow()
    private val resultFlow = _result.asSharedFlow()

    init {
        receiveIntents()
        processResults()
    }

    fun onIntent(intent: MviIntent) {
        viewModelScope.launch {
            withContext(Dispatchers.Main.immediate) { _intent.emit(intent) }
        }
    }

    private fun receiveIntents() {
        viewModelScope.launch {
            intentFlow
                .collect {
                    performActionInternal(it.toMviAction())
                }
        }
    }

    private fun processResults() {
        viewModelScope.launch {
            resultFlow
                .map { it.reduceWithState(state.value) }
                .collect { _state.value = it }
        }
    }

    private suspend fun performActionInternal(action: MviAction) {
        val currentState = state.value
        withContext(Dispatchers.Main.immediate) {
            startIndicatorFor(action)?.let { _result.emit(it) }
            _result.emit(performAction(action, currentState))
        }
    }

    private fun startIndicatorFor(action: MviAction): MviResult? {
        return when (action) {
            is ShowUsersListAction,
            is DeleteUserAction,
            is UpdateUserAction -> LoadingUsersResult
            else -> null
        }
    }

    private suspend fun performAction(
        action: MviAction,
        currentState: BaseState
    ): MviResult {
        return when (action) {
            is ShowUsersListAction -> ShowUsersListAction.performAction(usersRepository = usersRepository)
            is DeleteUserAction -> DeleteUserAction.performAction(
                action = action, state = currentState, usersRepository = usersRepository
            )
            is CreateUserAction -> CreateUserAction.performAction(
                action = action, usersRepository = usersRepository
            )
            is UpdateUserAction -> UpdateUserAction.performAction(
                action = action, usersRepository = usersRepository
            )
            is ShowCreateUserFormAction -> ShowCreateUserFormResult
            is ClearFilterAction -> ClearFilterResult
            is FilterByNameAction -> FilterByNameAction.performAction(action = action)
            else -> UnknownActionReceivedResult
        }
    }

}