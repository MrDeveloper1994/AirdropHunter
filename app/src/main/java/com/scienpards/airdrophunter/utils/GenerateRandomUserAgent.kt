package com.scienpards.airdrophunter.utils

import kotlin.random.Random

fun generateRandomUserAgent(deviceType: String = "android", browserType: String = "chrome"): String? {
    val chromeVersions = (110..126).toList()
    val firefoxVersions = (90..99).toList()

    val browserVersion = when (browserType) {
        "chrome" -> {
            val majorVersion = chromeVersions.random()
            val minorVersion = Random.nextInt(0, 10)
            val buildVersion = Random.nextInt(1000, 10000)
            val patchVersion = Random.nextInt(0, 100)
            "$majorVersion.$minorVersion.$buildVersion.$patchVersion"
        }
        "firefox" -> firefoxVersions.random().toString()
        else -> return null
    }

    return when (deviceType) {
        "android" -> {
            val androidVersions = listOf("10.0", "11.0", "12.0", "13.0")
            val androidDevices = listOf(
                "SM-G960F", "Pixel 5", "SM-A505F", "Pixel 4a", "Pixel 6 Pro", "SM-N975F",
                "SM-G973F", "Pixel 3", "SM-G980F", "Pixel 5a", "SM-G998B", "Pixel 4",
                "SM-G991B", "SM-G996B", "SM-F711B", "SM-F916B", "SM-G781B", "SM-N986B",
                "SM-N981B", "Pixel 2", "Pixel 2 XL", "Pixel 3 XL", "Pixel 4 XL",
                "Pixel 5 XL", "Pixel 6", "Pixel 6 XL", "Pixel 6a", "Pixel 7", "Pixel 7 Pro",
                "OnePlus 8", "OnePlus 8 Pro", "OnePlus 9", "OnePlus 9 Pro", "OnePlus Nord", "OnePlus Nord 2",
                "OnePlus Nord CE", "OnePlus 10", "OnePlus 10 Pro", "OnePlus 10T", "OnePlus 10T Pro",
                "Xiaomi Mi 9", "Xiaomi Mi 10", "Xiaomi Mi 11", "Xiaomi Redmi Note 8", "Xiaomi Redmi Note 9",
                "Huawei P30", "Huawei P40", "Huawei Mate 30", "Huawei Mate 40", "Sony Xperia 1",
                "Sony Xperia 5", "LG G8", "LG V50", "LG V60", "Nokia 8.3", "Nokia 9 PureView"
            )
            val androidVersion = androidVersions.random()
            val androidDevice = androidDevices.random()
            when (browserType) {
                "chrome" -> "Mozilla/5.0 (Linux; Android $androidVersion; $androidDevice) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/$browserVersion Mobile Safari/537.36"
                "firefox" -> "Mozilla/5.0 (Android $androidVersion; Mobile; rv:$browserVersion.0) Gecko/$browserVersion.0 Firefox/$browserVersion.0"
                else -> null
            }
        }
        "ios" -> {
            val iosVersions = listOf("13.0", "14.0", "15.0", "16.0")
            val iosVersion = iosVersions.random().replace(".", "_")
            when (browserType) {
                "chrome" -> "Mozilla/5.0 (iPhone; CPU iPhone OS $iosVersion like Mac OS X) AppleWebKit/537.36 (KHTML, like Gecko) CriOS/$browserVersion Mobile/15E148 Safari/604.1"
                "firefox" -> "Mozilla/5.0 (iPhone; CPU iPhone OS $iosVersion like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) FxiOS/$browserVersion.0 Mobile/15E148 Safari/605.1.15"
                else -> null
            }
        }
        "windows" -> {
            val windowsVersions = listOf("10.0", "11.0")
            val windowsVersion = windowsVersions.random()
            when (browserType) {
                "chrome" -> "Mozilla/5.0 (Windows NT $windowsVersion; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/$browserVersion Safari/537.36"
                "firefox" -> "Mozilla/5.0 (Windows NT $windowsVersion; Win64; x64; rv:$browserVersion.0) Gecko/$browserVersion.0 Firefox/$browserVersion.0"
                else -> null
            }
        }
        "ubuntu" -> {
            when (browserType) {
                "chrome" -> "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:94.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/$browserVersion Safari/537.36"
                "firefox" -> "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:$browserVersion.0) Gecko/$browserVersion.0 Firefox/$browserVersion.0"
                else -> null
            }
        }
        else -> null
    }
}
