package com.scienpards.airdrophunter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomHoverButton(onClick: () -> Unit,text: String,style: TextStyle =MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimary  )) {
    var isPressed by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .background(
                if (isPressed) Color(0xFFC62828) else MaterialTheme.colorScheme.primary,
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
            .indication(interactionSource, rememberRipple(bounded = true))
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
