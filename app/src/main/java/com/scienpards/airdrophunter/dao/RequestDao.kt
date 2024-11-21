package com.scienpards.airdrophunter.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scienpards.airdrophunter.models.Log
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: Log)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogs(logs: List<Log>)

    @Query("SELECT * FROM logs")
      fun getAllLogs(): Flow<List<Log>>


    @Query("SELECT * FROM logs WHERE endpoint = :endpoint")
    suspend   fun filterByEndpoint(endpoint: String): List<Log>


    @Query("SELECT * FROM logs WHERE phoneNumber = :phoneNumber")
    suspend  fun filterByPhoneNumber(phoneNumber: Long): List<Log>


    @Query("SELECT * FROM logs WHERE statusCode = :statusCode")
    suspend   fun filterByStatusCode(statusCode: Int): List<Log>


    @Query("SELECT * FROM logs WHERE timestampResponse >= :DaysAgo")
    suspend  fun getLogsFromLastDays(DaysAgo: Long): List<Log>

    @Query("DELETE FROM logs WHERE timestampResponse < :olderThan")
    suspend fun deleteOldLogs(olderThan: Long)



}
