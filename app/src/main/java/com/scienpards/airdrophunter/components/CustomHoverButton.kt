package com.scienpards.airdrophunter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomHoverButton(onClick: () -> Unit,text: String,style: TextStyle =MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onPrimary  )) {
    var isPressed by remember { mutableStateOf(false) }
//    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .background(
                if (isPressed) Color(0xFFFC9797) else MaterialTheme.colorScheme.primary,
                MaterialTheme.shapes.small
            )
            .width(200.dp)
            .height(50.dp)
            .padding(10.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        try {
                            onClick()
                            tryAwaitRelease()
                        } finally {
                            isPressed = false
                        }
                    }
                )
            }
//                .shadow(12.dp, ambientColor = Color.Black)
//            .indication(
//                interactionSource = interactionSource,
//                indication = LocalIndication.current
//            )
        ,
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = text,
//            modifier = Modifier.padding(1.dp),
            style = style
        )
    }
}
