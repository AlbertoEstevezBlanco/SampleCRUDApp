package com.example.samplecrudapp_innocv.mvi.intent

import com.example.samplecrudapp_innocv.mvi.action.ShowUsersListAction
import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviIntent

class ShowUsersListIntent: MviIntent {
    override fun toMviAction(): MviAction {
        return ShowUsersListAction
    }
}