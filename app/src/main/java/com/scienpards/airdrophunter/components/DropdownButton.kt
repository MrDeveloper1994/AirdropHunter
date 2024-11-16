package com.scienpards.airdrophunter.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp


@Composable
fun DropdownButton(onClick: () -> Unit, isMenuVisible: MutableState<Boolean>, ) {
    Button(
        onClick = onClick, modifier = Modifier
            .padding(0.dp)
            .wrapContentHeight()
            , shape = RectangleShape, colors = ButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = "ویرایش")
        Spacer(modifier = Modifier.width(10.dp))
        AnimatedContent(targetState = isMenuVisible.value, label = "dropdown") { isVisible ->
            if (isVisible) {
                Icon(
                    Icons.Default.KeyboardArrowUp,
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Close menu"
                )
            } else {
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Open menu"
                )
            }
        }
    }
}

