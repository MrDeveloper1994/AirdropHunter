package com.scienpards.airdrophunter.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.scienpards.airdrophunter.repository.GoatsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

@HiltWorker
class MiniAppWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val goatsRepository: GoatsRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        try {
//            val users = listOf("User1", "User2", "User3")
//            val userId = inputData.getInt("user_id", -1)
//            val userName = inputData.getString("user_name") ?: "Unknown"
//            coroutineScope {
//                users.map { user ->
//                    async {
////                        processUser(user)
//                    }
//                }.awaitAll()
//            }
            goatsRepository.completeMission(context = applicationContext)
            goatsRepository.getAdv(context = applicationContext)
            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.retry()
        }

    }
    //private suspend fun fetchDataFromApi(): String {
//    // شبیه‌سازی یک درخواست شبکه با Retrofit
//    println("Fetching data from API...")
//    delay(1000) // شبیه‌سازی تأخیر شبکه
//    return "Sample Data from API"
//}
}

//private fun saveLogToPreferences(log: String) {
//    val prefs = applicationContext.getSharedPreferences("worker_logs", Context.MODE_PRIVATE)
//    val editor = prefs.edit()
//    editor.putString("last_log", log)
//    editor.apply()
//}
//val prefs = context.getSharedPreferences("worker_logs", Context.MODE_PRIVATE)
//return prefs.getString("last_log", "No logs available")


//class ApiRequestWorker(
//    context: Context,
//    workerParams: WorkerParameters,
//    private val apiService: com.scienpards.airdrophunter.api.ApiService // API service برای ارسال درخواست‌ها
//) : CoroutineWorker(context, workerParams) {
//override suspend fun doWork(): Result {
//    return try {
//        // بررسی اتصال به اینترنت
//        if (isNetworkAvailable()) {
//            // ارسال درخواست به سرور
//            apiService.sendRequest()
//            Result.success()
//        } else {
//            // اگر اینترنت موجود نباشد، کار در صف باقی خواهد ماند
//            Result.retry()
//        }
//    } catch (e: Exception) {
//        // در صورت بروز خطا، دوباره تلاش می‌کند
//        Result.retry()
//    }
//}
//
//// متد برای بررسی اتصال اینترنت
//private fun isNetworkAvailable(): Boolean {
//    val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    val activeNetwork = connectivityManager.activeNetworkInfo
//    return activeNetwork != null && activeNetwork.isConnected
//}
//}


//private fun showNotification(title: String, message: String) {
//    val notificationManager =
//        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//    val channelId = "worker_channel_id"
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//        val channel = NotificationChannel(
//            channelId, "Worker Notifications", NotificationManager.IMPORTANCE_HIGH
//        )
//        notificationManager.createNotificationChannel(channel)
//    }
//
//    val notification = NotificationCompat.Builder(applicationContext, channelId)
//        .setContentTitle(title)
//        .setContentText(message)
//        .setSmallIcon(R.drawable.ic_notification) // آیکون مناسب
//        .build()
//
//    notificationManager.notify(1, notification)
//}
//}