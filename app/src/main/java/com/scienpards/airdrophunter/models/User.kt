package com.scienpards.airdrophunter.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val phone: Long?,
    val userId: String?,
    val userHash: String?,
    val notPixel: String?
)