package com.scienpards.airdrophunter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scienpards.airdrophunter.dao.LogDao
import com.scienpards.airdrophunter.models.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogViewModel @Inject constructor(
    private val logDao: LogDao
) : ViewModel() {


    private val _logs = MutableStateFlow<List<Log>>(emptyList())
    val logs: StateFlow<List<Log>> = _logs

    init {
        loadLogs()
    }

    private fun loadLogs() {

        viewModelScope.launch {
            logDao.getAllLogs()
                .collect { result ->
                    _logs.value = result
                }
        }
    }


    fun insertLog(log: Log) {
        viewModelScope.launch {
            logDao.insertLog(log)
        }
    }


    fun insertLogs(logs: List<Log>) {
        viewModelScope.launch {
            logDao.insertLogs(logs)
        }
    }

//
//  suspend  fun filterByEndpoint(endpoint: String): List<Log> {
//
//           return  logDao.filterByEndpoint(endpoint)
//
//
//    }
//
//  suspend  fun filterByPhoneNumber(phoneNumber: Long): List<Log> {
//
//          return logDao.filterByPhoneNumber(phoneNumber)
//
//    }
//
//
//  suspend  fun filterByStatusCode(statusCode: Int): List<Log> {
//
//          return logDao.filterByStatusCode(statusCode)
//
//    }


//    suspend fun filterFromLastDays(daysAgo: Int): List<Log> {
//            val currentTime = System.currentTimeMillis()
//            val daysAgoFromCurrent: Long = currentTime - (daysAgo * 24 * 60 * 60 * 1000)
//           return logDao.getLogsFromLastDays(daysAgoFromCurrent)
//
//    }


//    fun deleteOldLogs(olderThan: Long) {
//        viewModelScope.launch {
//            logDao.deleteOldLogs(olderThan)
//        }
//    }
}

