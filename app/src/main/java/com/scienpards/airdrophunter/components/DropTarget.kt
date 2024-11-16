package com.scienpards.airdrophunter.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.scienpards.airdrophunter.dataManager.UserModel
import com.scienpards.airdrophunter.models.User
import kotlin.math.pow
import kotlin.math.sqrt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DropTarget( onUserDeleted: () -> Unit) {
    val userModel: UserModel = hiltViewModel()
//    var isDropActive by remember { mutableStateOf(false) }

//    val iconSize by animateDpAsState(
//        targetValue = if (isDropActive) 80.dp else 64.dp // اندازه آیکون
//    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
//            .padding(15.dp)
            .dragAndDropTarget(
                target = object : DragAndDropTarget {

                    override fun onEntered(event: DragAndDropEvent) {
//                        isDropActive = true
                    }

                    override fun onEnded(event: DragAndDropEvent) {
//                        isDropActive = false
                    }

                    override fun onExited(event: DragAndDropEvent) {
//                        isDropActive = false
                    }

                    override fun onDrop(event: DragAndDropEvent): Boolean {

//                        isDropActive = false
                        val clipData = event.toAndroidDragEvent().clipData
                        if (clipData != null && clipData.itemCount > 0) {
                            val data = clipData.getItemAt(0).text.toString()
                            val parts = data.split(",")
                            val phone = parts
                                .getOrNull(1)
                                ?.toLongOrNull()
                            if (phone != null) {
                                userModel.deleteUserByPhone(phone)
                                onUserDeleted()

                            }
                        }
                        return true
                    }


                },
                shouldStartDragAndDrop = {
                    true
                }
            )
            .background(MaterialTheme.colorScheme.secondary)

    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Trash Bin",
            tint =  MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.Center)
        )
    }
}
