package com.example.samplecrudapp_innocv.mvi.action

import com.example.samplecrudapp_innocv.model.User
import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviResult
import com.example.samplecrudapp_innocv.mvi.result.ShowCreateUserFormResult
import com.example.samplecrudapp_innocv.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

data class CreateUserAction(
    val username: String,
    val birthDateString: String
) : MviAction {
    companion object {
        suspend fun performAction(
            action: CreateUserAction,
            usersRepository: UsersRepository
        ): MviResult {
            val birthDate = convertBirthDateStringToDate(action.birthDateString)
            birthDate?.let {
                withContext(Dispatchers.IO) {
                    usersRepository.postUser(
                        User(null, action.username, birthDate)
                    )
                }
            }

            return ShowCreateUserFormResult
        }


        private fun convertBirthDateStringToDate(birthDateString: String): Date? {
            val parser = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return parser.parse(birthDateString)
        }
    }
}
