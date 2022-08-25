package com.example.samplecrudapp_innocv.mvi.intent

import com.example.samplecrudapp_innocv.mvi.action.ClearFilterAction
import com.example.samplecrudapp_innocv.mvi.action.FilterByNameAction
import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviIntent

class FilterByNameIntent(val username: String): MviIntent {
    override fun toMviAction(): MviAction {
        return FilterByNameAction(username)
    }
}

object ClearFilterIntent: MviIntent {
    override fun toMviAction(): MviAction {
        return ClearFilterAction
    }
}