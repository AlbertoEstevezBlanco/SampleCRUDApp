package com.example.samplecrudapp_innocv.mvi.intent

import com.example.samplecrudapp_innocv.mvi.action.CreateUserAction
import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviIntent


data class CreateUserIntent(
    val username: String,
    val birthDateString: String
): MviIntent {
    override fun toMviAction(): MviAction {
        return CreateUserAction(username, birthDateString)
    }
}