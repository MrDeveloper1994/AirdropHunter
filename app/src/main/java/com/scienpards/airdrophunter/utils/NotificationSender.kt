package com.scienpards.airdrophunter.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.scienpards.airdrophunter.models.GoatsModel
import okhttp3.OkHttpClient
import okhttp3.Response
import javax.inject.Inject

//
//interface GoatsService {
//    suspend fun getMissions(token: String,userAgent: String): List<GoatsModel>
//    suspend fun completeMission(missionId: GoatsModel, token: String, userAgent: String): Response
//}
//
//class GoatsServiceImpl @Inject constructor(
//    private val okHttpClient: OkHttpClient,
//    private val gson: Gson
//) : GoatsService {




interface NotificationSender{
    suspend fun notificationSender(  context: Context,
                                      title: String,
                                      message: String):Unit
}
class NotificationSenderImpl:NotificationSender {

 override suspend fun notificationSender(
        context: Context,
        title: String,
        message: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notificationStructure(
                    title = title,
                    message = message,
                    context = context
                )
            } else {
                Toast.makeText(
                    context,
                    "Please provide Notification Permission from 'App Info' for send Token Expired Info",
                    Toast.LENGTH_SHORT
                ).show()


            }
        } else {
            notificationStructure(
                title = title,
                message = message,
                context = context
            )
        }


    }
}