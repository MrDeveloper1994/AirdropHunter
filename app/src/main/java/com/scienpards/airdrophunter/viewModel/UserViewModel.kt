package com.scienpards.airdrophunter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scienpards.airdrophunter.dao.UserDao
import com.scienpards.airdrophunter.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    val users: StateFlow<List<UserModel>> = userDao.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())




    fun addUser(userModel: UserModel) {
        viewModelScope.launch {
            userDao.insertUser(userModel)
        }
    }

    fun deleteUser(userModel: UserModel) {
        viewModelScope.launch {
            userDao.deleteUser(userModel)

        }
    }


    fun editUser(userModel: UserModel) {
        viewModelScope.launch {
            userDao.updateUser(userModel)
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


    suspend fun findUserByPhone(phone: Long): UserModel? {
        return userDao.findUserByPhone(phone)
    }



}


//val users by userModel.users.collectAsState()
//LazyColumn {
//    items(users) { user ->
//        Text(user.name)
//    }
//}