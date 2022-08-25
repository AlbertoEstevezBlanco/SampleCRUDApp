package com.example.samplecrudapp_innocv.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.Composable

@Composable
fun SampleCRUDApp_INNOCVTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = darkColors(),
        typography = Typography,
        content = content
    )
}