package com.example.samplecrudapp_innocv.mvi.base

import androidx.compose.runtime.Composable

interface MviView<I : MviIntent, in S : BaseState> {
    fun onIntent(intent: MviIntent)
    @Composable
    fun Render(state: BaseState)
}