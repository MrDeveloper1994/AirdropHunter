package com.scienpards.airdrophunter.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertToTehranTime(timestamp: Long): String {
    val instant = Instant.ofEpochMilli(timestamp)
    val zoneId = ZoneId.of("Asia/Tehran")
    val zonedDateTime = instant.atZone(zoneId)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return zonedDateTime.format(formatter)
}