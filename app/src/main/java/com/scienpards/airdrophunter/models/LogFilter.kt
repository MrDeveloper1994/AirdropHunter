package com.scienpards.airdrophunter.models

data class LogFilter(
    var endpoint: String? = null,
    var statusCode: Int? = null,
    var phoneNumber: String? = null,
    var timeStampResponce:Long?=null
)
