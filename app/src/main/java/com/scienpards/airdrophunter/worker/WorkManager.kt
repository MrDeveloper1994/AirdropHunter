package com.scienpards.airdrophunter.worker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.scienpards.airdrophunter.utils.getDelayUntilSpecificTime
import com.scienpards.airdrophunter.utils.isInternetConnected
import com.scienpards.airdrophunter.utils.isVpnConnected
import java.util.concurrent.TimeUnit

fun goatsWorkManager(context: Context= ApplicationProvider.getApplicationContext()) {

    if (isInternetConnected(context) && isVpnConnected(context)) {
        val delayUntil4AM = getDelayUntilSpecificTime((0..4).random(),(0..59).random())
        val workRequest4AM = OneTimeWorkRequestBuilder<MiniAppWorker>()
            .setInitialDelay(delayUntil4AM, TimeUnit.MILLISECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context).enqueue(workRequest4AM)
    } else {
//
    }
    if (isInternetConnected(context) && isVpnConnected(context)) {
        val delayUntil8AM = getDelayUntilSpecificTime((4..8).random(),(0..59).random())
        val workRequest8AM = OneTimeWorkRequestBuilder<MiniAppWorker>()
            .setInitialDelay(delayUntil8AM, TimeUnit.MILLISECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context).enqueue(workRequest8AM)
    } else {
//
    }
    if (isInternetConnected(context) && isVpnConnected(context)) {
        val delayUntil12AM = getDelayUntilSpecificTime((8..12).random(),(0..59).random())
        val workRequest12AM = OneTimeWorkRequestBuilder<MiniAppWorker>()
            .setInitialDelay(delayUntil12AM, TimeUnit.MILLISECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context).enqueue(workRequest12AM)
    } else {
//
    }
       if (isInternetConnected(context) && isVpnConnected(context)) {
        val delayUntil16AM = getDelayUntilSpecificTime((12..16).random(),(0..59).random())
        val workRequest16AM = OneTimeWorkRequestBuilder<MiniAppWorker>()
            .setInitialDelay(delayUntil16AM, TimeUnit.MILLISECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context).enqueue(workRequest16AM)
    } else {
//
    }
           if (isInternetConnected(context) && isVpnConnected(context)) {
        val delayUntil20AM = getDelayUntilSpecificTime((16..20).random(),(0..59).random())
        val workRequest20AM = OneTimeWorkRequestBuilder<MiniAppWorker>()
            .setInitialDelay(delayUntil20AM, TimeUnit.MILLISECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context).enqueue(workRequest20AM)
    } else {
//
    }
             if (isInternetConnected(context) && isVpnConnected(context)) {
        val delayUntil24AM = getDelayUntilSpecificTime((20..24).random(),(0..59).random())
        val workRequest24AM = OneTimeWorkRequestBuilder<MiniAppWorker>()
            .setInitialDelay(delayUntil24AM, TimeUnit.MILLISECONDS)
//                 val inputData = Data.Builder()
//                     .putInt("user_id", userId)
//                     .putString("user_name", userName)
//                     .build()
//            .setInputData(inputData)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context).enqueue(workRequest24AM)
    } else {
//
    }





}
