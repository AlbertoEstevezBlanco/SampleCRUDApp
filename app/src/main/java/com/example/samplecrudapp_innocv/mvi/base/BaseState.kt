package com.example.samplecrudapp_innocv.mvi.base

import com.example.samplecrudapp_innocv.model.User

data class BaseState(
    val users: List<User>?,
    val showList: Boolean,
    val nameFilter: ((User) -> Boolean)?,
    val errorMsg: String?,
    val isLoading: Boolean
)

val initialState = BaseState(
    users = emptyList(),
    showList = false,
    nameFilter = null,
    errorMsg = null,
    isLoading = false
)