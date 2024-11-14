//package com.scienpards.airdrophunter.dao
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.scienpards.airdrophunter.dataManager.UserModel
//
//class UserModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(UserModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return UserModel(userDao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
