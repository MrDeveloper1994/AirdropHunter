package com.scienpards.airdrophunter.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.scienpards.airdrophunter.dataManager.UserModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DropTarget(modifier: Modifier) {
    val userModel: UserModel = hiltViewModel()
    var isDropActive by remember { mutableStateOf(false) }
    val iconSize by animateDpAsState(
        targetValue = if (isDropActive) 80.dp else 64.dp // اندازه آیکون
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .dragAndDropTarget(
                target = object : DragAndDropTarget {

                    override fun onStarted(event: DragAndDropEvent) {
                        isDropActive = false


                    }


                    override fun onEnded(event: DragAndDropEvent) {

                        isDropActive = false


                    }

                    override fun onExited(event: DragAndDropEvent) {

                        isDropActive = false


                    }

                    override fun onDrop(event: DragAndDropEvent): Boolean {
                        isDropActive = false
                        val clipData = event.toAndroidDragEvent().clipData
                        if (clipData != null && clipData.itemCount > 0) {
                            val data = clipData.getItemAt(0).text.toString()
                            val parts = data.split(",")
                            val phone = parts
                                .getOrNull(1)
                                ?.toLongOrNull()
//                            println("data ${data} parts ${parts}phone${phone}")
                            if (phone != null) {
                                userModel.deleteUserByPhone(phone)
                            }
                        }

                        return true
                    }

                    override fun onEntered(event: DragAndDropEvent) {

                        isDropActive = true
                    }
                },
                shouldStartDragAndDrop = { true }
            )
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        LaunchedEffect(isDropActive) {
            if (isDropActive) {
                kotlinx.coroutines.delay(3000)
                isDropActive = false
            }
        }
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Trash Bin",
            tint = if (isDropActive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(iconSize)
                .align(Alignment.Center)
        )
    }
}
