package com.example.samplecrudapp_innocv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.samplecrudapp_innocv.repository.UsersRepository

class MainViewModelFactory(
    private val usersRepository: UsersRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(usersRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}