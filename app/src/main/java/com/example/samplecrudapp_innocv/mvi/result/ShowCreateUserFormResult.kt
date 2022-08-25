package com.example.samplecrudapp_innocv.mvi.result

import com.example.samplecrudapp_innocv.mvi.base.BaseState
import com.example.samplecrudapp_innocv.mvi.base.MviResult

object ShowCreateUserFormResult : MviResult {
    override fun reduceWithState(state: BaseState): BaseState {
        return state.copy(showList = false, isLoading = false)
    }
}