package com.scienpards.airdrophunter.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.scienpards.airdrophunter.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>


    @Delete
    suspend fun deleteUser(user: User)


    @Update
    suspend fun updateUser(user: User)


    @Query("SELECT * FROM users WHERE phone = :phone LIMIT 1")
    suspend fun findUserByPhone(phone: Long): User?


    @Query("DELETE FROM users WHERE phone = :phone")
    suspend fun deleteUserByPhone(phone: Long)


    @Query("UPDATE users SET userId = :userId, userHash = :userHash, notPixel = :notPixel WHERE phone = :phone")
    suspend fun updateUserByPhone(phone: Long, userId: String?, userHash: String?, notPixel: String?)

}
