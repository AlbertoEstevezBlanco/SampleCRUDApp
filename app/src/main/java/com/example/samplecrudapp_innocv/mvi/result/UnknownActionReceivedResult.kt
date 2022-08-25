package com.example.samplecrudapp_innocv.mvi.result

import com.example.samplecrudapp_innocv.mvi.base.BaseState
import com.example.samplecrudapp_innocv.mvi.base.MviResult

object UnknownActionReceivedResult : MviResult {

    override fun reduceWithState(state: BaseState): BaseState {
        return state.copy()
    }
}