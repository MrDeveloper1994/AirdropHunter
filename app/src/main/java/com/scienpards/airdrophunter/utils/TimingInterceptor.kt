package com.scienpards.airdrophunter.utils

import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TimingInterceptor(
    private val timingFlow: MutableStateFlow<RequestTimingState>
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestTimestamp = System.currentTimeMillis()

        val response = chain.proceed(chain.request())

        val responseTimestamp = System.currentTimeMillis()


        timingFlow.value = RequestTimingState(
            requestTime = requestTimestamp,
            responseTime = responseTimestamp,
            duration = responseTimestamp - requestTimestamp
        )

        return response
    }
}

data class RequestTimingState(
    val requestTime: Long = 0L,
    val responseTime: Long = 0L,
    val duration: Long = 0L
)
