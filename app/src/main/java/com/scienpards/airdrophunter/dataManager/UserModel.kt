package com.scienpards.airdrophunter.dataManager

import androidx.lifecycle.ViewModel
import com.scienpards.airdrophunter.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserModel : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    fun addUser(user: User) {
        _users.value += user
    }
}