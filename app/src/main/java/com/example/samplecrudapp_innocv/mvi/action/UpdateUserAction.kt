package com.example.samplecrudapp_innocv.mvi.action

import com.example.samplecrudapp_innocv.model.User
import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviResult
import com.example.samplecrudapp_innocv.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class UpdateUserAction(
    val userId: Int?,
    val username: String,
    val birthDateString: String
) : MviAction {
    companion object {
        suspend fun performAction(
            action: UpdateUserAction,
            usersRepository: UsersRepository
        ): MviResult {
            val birthDate = convertBirthDateStringToDate(action.birthDateString)
            birthDate?.let {
                withContext(Dispatchers.IO) {
                    usersRepository.updateUser(
                        User(action.userId, action.username, birthDate)
                    )
                }
            }

            return ShowUsersListAction.performAction(usersRepository)
        }


        private fun convertBirthDateStringToDate(birthDateString: String): Date? {
            val parser = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return parser.parse(birthDateString)
        }
    }
}