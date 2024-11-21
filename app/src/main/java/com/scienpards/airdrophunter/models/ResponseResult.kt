package com.scienpards.airdrophunter.models

data class ResponseResult(
    val requestTime: Long,
    val responseTime: Long,
    val response: okhttp3.Response,
    val request : okhttp3.Request?
)