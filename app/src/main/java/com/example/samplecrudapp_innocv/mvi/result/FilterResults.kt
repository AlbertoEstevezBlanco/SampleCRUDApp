package com.example.samplecrudapp_innocv.mvi.result

import com.example.samplecrudapp_innocv.model.User
import com.example.samplecrudapp_innocv.mvi.base.BaseState
import com.example.samplecrudapp_innocv.mvi.base.MviResult

class FilterByNameResult(private val filter: (User) -> Boolean) : MviResult {
    override fun reduceWithState(state: BaseState): BaseState {
        return state.copy(
            nameFilter = filter
        )
    }
}

object ClearFilterResult: MviResult {
    override fun reduceWithState(state: BaseState): BaseState {
        return state.copy(nameFilter = null)
    }
}