package com.example.samplecrudapp_innocv.mvi.result

import com.example.samplecrudapp_innocv.model.User
import com.example.samplecrudapp_innocv.mvi.base.BaseState
import com.example.samplecrudapp_innocv.mvi.base.MviResult

data class ShowUsersListResult(
    val users: List<User>
) : MviResult {
    override fun reduceWithState(state: BaseState): BaseState {
        return state.copy(
            users = users,
            showList = true,
            isLoading = false
        )
    }
}

data class NoUsersAvailableResult(
    val errorMsg: String
) : MviResult {
    override fun reduceWithState(state: BaseState): BaseState {
        return state.copy(
            users = emptyList(),
            showList = true,
            errorMsg = errorMsg,
            isLoading = false
        )
    }
}

object LoadingUsersResult: MviResult {
    override fun reduceWithState(state: BaseState): BaseState {
        return state.copy(isLoading = true)
    }
}