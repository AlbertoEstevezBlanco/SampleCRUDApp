package com.example.samplecrudapp_innocv.mvi.intent

import com.example.samplecrudapp_innocv.mvi.action.DeleteUserAction
import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviIntent

class DeleteUserIntent(private val userId: Int) : MviIntent {

    override fun toMviAction(): MviAction {
        return DeleteUserAction(userId)
    }

}