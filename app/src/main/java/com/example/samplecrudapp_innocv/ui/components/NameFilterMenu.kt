package com.example.samplecrudapp_innocv.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun NameFilterMenu(
    displayMenu: MutableState<Boolean>,
    clearNameFilterCallback: () -> Unit,
    filterByNameCallback: (username: String) -> Unit
) {
    val nameToFilter = remember { mutableStateOf("") }
    UsernameField(fieldState = nameToFilter)
    IconButton(onClick = {
        filterByNameCallback(nameToFilter.value)
    }) { Icon(imageVector = Icons.Filled.Send, contentDescription = "Filter button") }
    IconButton(onClick = {
        displayMenu.value = false
        clearNameFilterCallback()
    }) { Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear filter button") }
}