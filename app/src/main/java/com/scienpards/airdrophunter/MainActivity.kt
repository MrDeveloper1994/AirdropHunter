package com.scienpards.airdrophunter

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.scienpards.airdrophunter.ui.theme.AirdropHunterTheme
import com.scienpards.airdrophunter.worker.goatsWorkManager
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    private val requestNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(applicationContext, "Notification Activated", Toast.LENGTH_SHORT)

            } else {
                Toast.makeText(
                    applicationContext,
                    "Please provide Notification Permission from 'App Info' for send Token Expired Info",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }

    //    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            AirdropHunterTheme(darkTheme = false) {

                window.navigationBarColor = MaterialTheme.colorScheme.secondary.toArgb()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    window.navigationBarDividerColor = MaterialTheme.colorScheme.secondary.toArgb()
                }
                window.statusBarColor = MaterialTheme.colorScheme.secondary.toArgb()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    MainNavigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                        context=applicationContext
                    )
                }
            }

        }
        checkAndRequestNotificationPermission()
        goatsWorkManager(context = applicationContext)

    }

    private fun checkAndRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
//            else {
//                // اجرا کردن Worker
//                startNotificationWorker()
//            }
//        } else {
//            // نسخه‌های پایین‌تر از Android 13
//            startNotificationWorker()
//        }
        }
    }
}
//    private fun startNotificationWorker() {
//        // تعریف یک کار برای WorkManager
//        val notificationWorkRequest: WorkRequest =
//            OneTimeWorkRequestBuilder<NotificationWorker>().build()
//
//        // اجرا کردن کار
//        WorkManager.getInstance(this).enqueue(notificationWorkRequest)
//    }

//    private val requestNotificationPermissionLauncher =
//        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
//            if (isGranted) {
//                startNotificationWorker()
//            } else {
//                // مجوز رد شده است
//            }
//        }








