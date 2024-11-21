package com.scienpards.airdrophunter.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.scienpards.airdrophunter.models.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userModel: UserModel)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserModel>>


    @Delete
    suspend fun deleteUser(userModel: UserModel)


    @Update
    suspend fun updateUser(userModel: UserModel)


    @Query("SELECT * FROM users WHERE phone = :phone LIMIT 1")
    suspend fun findUserByPhone(phone: Long): UserModel?


    @Query("DELETE FROM users WHERE phone = :phone")
    suspend fun deleteUserByPhone(phone: Long)


    @Query("UPDATE users SET userId = :userId, userHash = :userHash, goats = :notPixel WHERE phone = :phone")
    suspend fun updateUserByPhone(phone: Long, userId: String?, userHash: String?, notPixel: String?)

}
