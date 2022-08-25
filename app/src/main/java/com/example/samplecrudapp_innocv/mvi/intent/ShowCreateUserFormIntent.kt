package com.example.samplecrudapp_innocv.mvi.intent

import com.example.samplecrudapp_innocv.mvi.action.ShowCreateUserFormAction
import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviIntent

class ShowCreateUserFormIntent : MviIntent {
    override fun toMviAction(): MviAction {
        return ShowCreateUserFormAction
    }
}