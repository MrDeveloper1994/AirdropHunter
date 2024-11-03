package com.scienpards.airdrophunter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.TextUnit

@Composable
fun GradientText() {
    var gradientColors by remember { mutableStateOf(listOf(Color.Blue, Color.Green)) }

    LaunchedEffect(Unit) {
        while (true) {
            gradientColors = listOf(
                Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat()),
                Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat())
            )

            delay(Random.nextLong(3000, 5000))
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawGradientText(
            text = """
                Airdrop Hunter
           """.trimIndent(),
            gradientColors = gradientColors,
            fontSize = 80.sp
        )
    }
}

fun DrawScope.drawGradientText(text: String, gradientColors: List<Color>, fontSize: TextUnit) {
    val paint = android.graphics.Paint().apply {
        this.shader = android.graphics.LinearGradient(0f, 0f, 0f, 100f,
            intArrayOf(
                gradientColors[0].toArgb(),
                gradientColors[1].toArgb()
            ), null, android.graphics.Shader.TileMode.CLAMP)
        this.textSize = 80.sp.value
        this.isAntiAlias = true
    }

    val textWidth = paint.measureText(text)

    drawContext.canvas.nativeCanvas.drawText(
        text,
        (size.width - textWidth) / 2,
        size.height / 2,
        paint
    )
}
