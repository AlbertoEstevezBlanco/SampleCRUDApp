package com.example.samplecrudapp_innocv.mvi.base

interface MviIntent {

    fun toMviAction(): MviAction
}