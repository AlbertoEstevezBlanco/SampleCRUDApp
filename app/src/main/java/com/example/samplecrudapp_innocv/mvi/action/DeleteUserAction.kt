package com.example.samplecrudapp_innocv.mvi.action

import com.example.samplecrudapp_innocv.mvi.base.BaseState
import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviResult
import com.example.samplecrudapp_innocv.mvi.result.ShowUsersListResult
import com.example.samplecrudapp_innocv.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteUserAction(val userId: Int) : MviAction {
    companion object {
        suspend fun performAction(
            action: DeleteUserAction,
            state: BaseState,
            usersRepository: UsersRepository
        ): MviResult =
            withContext(Dispatchers.IO){
                val userIdToDelete = action.userId
                val apiResponse = usersRepository.deleteUser(userIdToDelete)
                if (apiResponse.isSuccessful)
                    ShowUsersListResult(
                        users = state.users?.filter { it.id != userIdToDelete } ?: emptyList()
                    )
                else ShowUsersListResult(users = state.users?: emptyList())
            }
    }
}