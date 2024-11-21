package com.scienpards.airdrophunter.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.scienpards.airdrophunter.models.GoatsModel
import com.scienpards.airdrophunter.models.ResponseResult
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okio.IOException
import javax.inject.Inject

interface GoatsService {
    suspend fun getMissions(token: String, userAgent: String): List<GoatsModel>
    suspend fun completeMission(missionId: GoatsModel, token: String, userAgent: String): ResponseResult
    suspend fun getAdv( token: String, userAgent: String): ResponseResult
}

class GoatsServiceImpl @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson
) : GoatsService {

    private val baseUrl = "https://api-mission.goatsbot.xyz/"

    private val baseHeaders = mapOf(
        "Authorization" to "Bearer <your-token>"
    )

    private fun buildRequest(
        url: String,
        method: String,
        token: String,
        userAgent: String = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36",
        body: RequestBody? = null
    ): Request {
        val requestBuilder = Request.Builder()
            .url("$baseUrl$url")
            .method(method, body)
            .addHeader("Authorization", token)
            .addHeader("User-Agent", userAgent)

        for ((key, value) in baseHeaders) {
            requestBuilder.addHeader(key, value)
        }

        return requestBuilder.build()
    }

    override suspend fun getMissions(token: String, userAgent: String): List<GoatsModel> {
        val request = buildRequest("missions/user", "GET", token, userAgent)
        val response = okHttpClient.newCall(request).execute()

        if (!response.isSuccessful) {
//            throw IOException("Unexpected code $response")
            println("get mission Goats code $response")
        }

        val json = response.body?.string() ?: throw IOException("Empty response body")
        val missionType = object : TypeToken<List<GoatsModel>>() {}.type
        return gson.fromJson(json, missionType)
    }

    override suspend fun completeMission(
        missionId: GoatsModel,
        token: String,
        userAgent: String
    ): ResponseResult {


        val request = buildRequest("missions/action/$missionId", "POST", token, userAgent)
        val requestTime = System.currentTimeMillis()
//        try {
        val response = okHttpClient.newCall(request).execute()
        val responseTime = System.currentTimeMillis()
        val duration = responseTime - requestTime
//        } catch (e: IOException) {
//            println("complete Mission Goats code $response")
//        }


        return ResponseResult(requestTime,responseTime,response,request)
    }
    override suspend fun getAdv(
        token: String,
        userAgent: String
    ): ResponseResult {

        val request = buildRequest("https://dev-api.goatsbot.xyz/missions/action/66db47e2ff88e4527783327e", "POST", token, userAgent)
        val requestTime = System.currentTimeMillis()
//        try {
        val response = okHttpClient.newCall(request).execute()
        val responseTime = System.currentTimeMillis()
        val duration = responseTime - requestTime
//        } catch (e: IOException) {
//            println("complete Mission Goats code $response")
//        }


        return ResponseResult(requestTime,responseTime,response,request)
    }
}
//
//async function viewAdv() {
//    const res = await fetch(
//        "https://dev-api.goatsbot.xyz/missions/action/66db47e2ff88e4527783327e",
//        { method: "POST", headers }
//    )
//
//    const data = await res.json()
//    console.log("adv -", data.status ?? data.message)
//}
//
//async function makeMoney() {
//    const missions = await getMissions()
//    // await completeAllOfMissions(missions)
//
//    await viewAdv()
//    setInterval(viewAdv, 60000)
//}
//
//makeMoney()
//
//console.log("excuted: started...")







//
//
//'Accept': 'application/json, text/plain, */*',
//'Accept-Language': 'ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7',
//'Content-Type': 'application/json',
//'Connection': 'keep-alive',
//'Origin': 'https://app.notpx.app',
//'Referer': 'https://app.notpx.app',
//'Sec-Fetch-Dest': 'empty',
//'Sec-Fetch-Mode': 'cors',
//'Sec-Fetch-Site': 'same-origin',
//'Sec-Ch-Ua': '"Google Chrome";v="125", "Chromium";v="125", "Not.A/Brand";v="24"',
//'Sec-Ch-Ua-mobile': '?1',
//'Sec-Ch-Ua-platform': '"Android"',
//'User-Agent': 'Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.6422.165 Mobile Safari/537.36',
//'X-Requested-With': 'org.telegram.messenger'