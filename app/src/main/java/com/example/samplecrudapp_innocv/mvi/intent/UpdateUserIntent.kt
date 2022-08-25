package com.example.samplecrudapp_innocv.mvi.intent

import com.example.samplecrudapp_innocv.mvi.action.UpdateUserAction
import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviIntent

class UpdateUserIntent(
    private val userId: Int?,
    val username: String,
    private val birthDateString: String
) : MviIntent {
    override fun toMviAction(): MviAction {
        return UpdateUserAction(userId, username, birthDateString)
    }
}