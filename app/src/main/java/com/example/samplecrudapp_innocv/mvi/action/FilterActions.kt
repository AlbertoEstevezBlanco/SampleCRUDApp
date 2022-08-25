package com.example.samplecrudapp_innocv.mvi.action

import com.example.samplecrudapp_innocv.mvi.base.MviAction
import com.example.samplecrudapp_innocv.mvi.base.MviResult
import com.example.samplecrudapp_innocv.mvi.result.FilterByNameResult

class FilterByNameAction(val username: String): MviAction {
    companion object {
        fun performAction(action: FilterByNameAction): MviResult {
            return FilterByNameResult { user -> user.name == action.username }
        }
    }
}

object ClearFilterAction: MviAction