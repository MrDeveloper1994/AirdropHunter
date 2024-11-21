package com.scienpards.airdrophunter.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logs")
data class Log(
    @PrimaryKey(autoGenerate = true) val logId: Int = 0,
    val phoneNumber: Long,
    val statusMessage: String,
    val timestampRequest: Long ,
    val endpoint: String,
    val timestampResponse: Long,
    val statusCode: Int
)
