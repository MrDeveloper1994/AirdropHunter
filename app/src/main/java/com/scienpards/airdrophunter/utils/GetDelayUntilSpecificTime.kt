package com.scienpards.airdrophunter.utils

import java.util.*
import java.util.concurrent.TimeUnit


fun getDelayUntilSpecificTime(randomHour:Int,randomMinute:Int): Long {
//    val randomHour = (0..23).random()
//    val randomMinute = (0..59).random()
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    calendar.set(Calendar.HOUR_OF_DAY, randomHour)
    calendar.set(Calendar.MINUTE, randomMinute)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    val currentTime = System.currentTimeMillis()
    val targetTime = calendar.timeInMillis

    return if (targetTime > currentTime) {
        targetTime - currentTime
    } else {
        targetTime + TimeUnit.DAYS.toMillis(1) - currentTime
    }
}
