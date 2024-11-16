package com.scienpards.airdrophunter.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.scienpards.airdrophunter.models.User

@SuppressLint("SuspiciousIndentation")
@Composable
fun CustomRow(

    modifier: Modifier = Modifier,
    users: List<User>,
    isMenuVisible: MutableState<Boolean>,
    result: User?,
    resultAll: List<User>
) {
    val scrollSurfaceState = rememberScrollState()



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .horizontalScroll(scrollSurfaceState)
            .background(color = MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        if (result != null) {
            UserCard(user = result, isMenuVisible = isMenuVisible)
        } else if (resultAll.isNotEmpty()) {
            users.forEach { user ->
                UserCard(user = user, isMenuVisible = isMenuVisible)
            }

        }


    }

}
