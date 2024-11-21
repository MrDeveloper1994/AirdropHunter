package com.scienpards.airdrophunter.components
import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.material3.Text
//import androidx.compose.ui.tooling.data.EmptyGroup.data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.system.measureTimeMillis

@Composable
fun RealTimeInternetSpeed(context: Context) {
    var downloadSpeed by remember { mutableStateOf<String?>(null) }
    var uploadSpeed by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        while (true) {
            downloadSpeed = measureDownloadSpeed()
            uploadSpeed = measureUploadSpeed()
            kotlinx.coroutines.delay(12000L)
        }
    }

    Text(
        text = if (downloadSpeed != null || uploadSpeed != null) {
            "📥 ${downloadSpeed} Mbps / 📤 ${uploadSpeed} Mbps"
        } else {
            "...در حال محاسبه"
        }
    )
}

suspend fun measureDownloadSpeed(): String? {
    return withContext(Dispatchers.IO) {
        try {

            val url = URL("https://cachefly.cachefly.net/1mb.test")
            val connection = url.openConnection()
            connection.connect()

            val inputStream = connection.getInputStream()
            val buffer = ByteArray(1024)
            var totalBytesRead = 0

            val time = measureTimeMillis {
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    totalBytesRead += bytesRead
                    if (totalBytesRead >= 1024 * 1024) break
                }
            }

            inputStream.close()
            val speedMbps = (totalBytesRead * 8 / (time / 1000.0) / 1_000_000)
           String.format("%.3f", speedMbps)

//            (totalBytesRead * 8 / (time / 1000.0) / 1_000).toInt()

        } catch (e: Exception) {
            Log.d("DownloadSpeedTest", "Error during download: ${e.message}")

            e.printStackTrace()
            null
        }
    }
}

suspend fun measureUploadSpeed(): String? {
    return withContext(Dispatchers.IO) {
        try {
            val url = URL("https://postman-echo.com/post")
            val connection = url.openConnection()
            connection.doOutput = true

            val outputStream = connection.getOutputStream()
            val data = ByteArray(1024*1024)

            val time = measureTimeMillis {
                outputStream.write(data)
                outputStream.flush()
            }

            outputStream.close()

//            (data.size * 8 / (time / 1000.0) / 1_000_000).toInt()
            val speedMbps = (data.size * 8 / (time / 1000.0) / 1_000_000_000)
            String.format("%.3f", speedMbps)
        } catch (e: Exception) {
            null
        }
    }
}
