package com.scienpards.airdrophunter.dataManager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scienpards.airdrophunter.dao.UserDao
import com.scienpards.airdrophunter.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    val users: StateFlow<List<User>> = userDao.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())




    fun addUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userDao.deleteUser(user)

        }
    }


    fun editUser(user: User) {
        viewModelScope.launch {
            userDao.updateUser(user)
        }
    }

    fun deleteUserByPhone(phone: Long) {
        viewModelScope.launch {
            userDao.deleteUserByPhone(phone)
        }
    }


    fun updateUserByPhone(phone: Long, userId: String?, userHash: String?, notPixel: String?) {
        viewModelScope.launch {
            userDao.updateUserByPhone(phone, userId, userHash, notPixel)
        }
    }


    suspend fun findUserByPhone(phone: Long): User? {
        return userDao.findUserByPhone(phone)
    }



}


//val users by userModel.users.collectAsState()
//LazyColumn {
//    items(users) { user ->
//        Text(user.name)
//    }
//}