package com.scienpards.airdrophunter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.scienpards.airdrophunter.R

// Set of Material typography styles to start with
val MorvaridFontFamily = FontFamily(
    Font(R.font.morvarid, FontWeight.Normal),
//    Font(R.font.FarBeirut, FontWeight.Normal),
)
val FarBeirutFontFamily = FontFamily(
    Font(R.font.far_beirut, FontWeight.Normal),
//    Font(R.font.FarBeirut, FontWeight.Normal),
)
val FarNazaninFontFamily = FontFamily(
    Font(R.font.far_nazanin, FontWeight.Normal),
//    Font(R.font.FarBeirut, FontWeight.Normal),
)
val IranSansFontFamily = FontFamily(
    Font(R.font.iran_sans_fanum, FontWeight.Normal),
//    Font(R.font.FarBeirut, FontWeight.Normal),
)
val YekanFontFamily = FontFamily(
    Font(R.font.yekan, FontWeight.Normal),
//    Font(R.font.FarBeirut, FontWeight.Normal),
)
val B_ZarFontFamily = FontFamily(
    Font(R.font.b_zar_bold, FontWeight.Normal),
//    Font(R.font.FarBeirut, FontWeight.Normal),
)


val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = B_ZarFontFamily

    ),
    headlineMedium = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = B_ZarFontFamily
    ),
    titleSmall = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = IranSansFontFamily
    ),
    bodyMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = IranSansFontFamily
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = IranSansFontFamily
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Light,
        fontFamily = IranSansFontFamily
    )
)