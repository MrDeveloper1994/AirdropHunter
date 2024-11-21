package com.scienpards.airdrophunter.repository

import android.content.Context
import com.scienpards.airdrophunter.api.GoatsService
import com.scienpards.airdrophunter.dao.LogDao
import com.scienpards.airdrophunter.dao.UserDao
import com.scienpards.airdrophunter.models.Log
import com.scienpards.airdrophunter.utils.NotificationSender
import com.scienpards.airdrophunter.utils.generateRandomUserAgent
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random


class GoatsRepository @Inject constructor(
    private val goatsService: GoatsService,
    private val userDao: UserDao,
    private val notificationSender: NotificationSender,
    private val logDao: LogDao
) {

    suspend fun completeMission(context: Context) {

        coroutineScope {
            val missionResponses = mutableListOf<Pair<String, Int>>()
            userDao.getAllUsers().collect { userList ->
                userList.take(1).map { user ->
                    async {
                        user.goats.let {
                            val userAgent = lazy { generateRandomUserAgent() }
                            goatsService.getMissions(
                                it,
                                userAgent.toString()
                            ).forEach { missionId ->

                                val responseResult =
                                    goatsService.completeMission(
                                        missionId,
                                        user.goats,
                                        userAgent.toString()
                                    )
                                delay((20000..60000).random().toLong())
                                missionResponses.add(Pair(responseResult.response.message, responseResult.response.code))

                                logDao.insertLog(Log(
                                    phoneNumber = user.phone,
                                    statusMessage = responseResult.response.message,
                                    timestampRequest = responseResult.requestTime,
                                    endpoint = "Goats",
                                    timestampResponse = responseResult.responseTime,
                                    statusCode = responseResult.response.code
                                ))
                            }
                            if (missionResponses[1].second == 401) {
                                notificationSender.notificationSender(
                                    context, "status code ${missionResponses[1].second}",
                                    missionResponses[0].first
                                )
                            }
                        }

                    }

                }.awaitAll()
            }


        }
    }
    suspend fun getAdv(context: Context) {

        coroutineScope {
            val missionResponses = mutableListOf<Pair<String, Int>>()
            userDao.getAllUsers().collect { userList ->
                userList.take(1).map { user ->
                    async {
                        user.goats.let {
                            val userAgent = lazy { generateRandomUserAgent() }
                            val repetitions = Random.nextInt(12, 21)

                           for (i in 11..repetitions){
                                val responseResult =
                                    goatsService.getAdv(
                                        user.goats,
                                        userAgent.toString()
                                    )
                                delay((60000..100000).random().toLong())
                                missionResponses.add(Pair(responseResult.response.message, responseResult.response.code))

                                logDao.insertLog(Log(
                                    phoneNumber = user.phone,
                                    statusMessage = responseResult.response.message,
                                    timestampRequest = responseResult.requestTime,
                                    endpoint = "Goats",
                                    timestampResponse = responseResult.responseTime,
                                    statusCode = responseResult.response.code
                                ))
                            }
                            if (missionResponses[1].second == 401) {
                                notificationSender.notificationSender(
                                    context, "status code ${missionResponses[1].second}",
                                    missionResponses[0].first
                                )
                            }
                        }

                    }

                }.awaitAll()
            }


        }
    }
}