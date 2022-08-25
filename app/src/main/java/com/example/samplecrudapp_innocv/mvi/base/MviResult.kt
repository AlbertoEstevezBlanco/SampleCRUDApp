package com.example.samplecrudapp_innocv.mvi.base

interface MviResult {

    fun reduceWithState(state: BaseState): BaseState
}