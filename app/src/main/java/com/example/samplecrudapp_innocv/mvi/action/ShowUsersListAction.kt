package com.example.samplecrudapp_innocv.mvi.action

import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviResult
import com.example.samplecrudapp_innocv.mvi.result.NoUsersAvailableResult
import com.example.samplecrudapp_innocv.mvi.result.ShowUsersListResult
import com.example.samplecrudapp_innocv.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ShowUsersListAction : MviAction {

    suspend fun performAction(usersRepository: UsersRepository): MviResult = withContext(Dispatchers.IO) {
        val usersResponse = usersRepository.getUsers()
        if (usersResponse.isSuccessful) {
            val users = usersResponse.body()
            users?.let { ShowUsersListResult(users) }
                ?: NoUsersAvailableResult(errorMsg = "No users seem to be available in backend")
        } else {
            NoUsersAvailableResult(errorMsg = usersResponse.errorBody()?.toString() ?: "Something wrong happened")
        }
    }
}