package com.scienpards.airdrophunter.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.scienpards.airdrophunter.models.UserModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun CustomRow(

    modifier: Modifier = Modifier,
    userModels: List<UserModel>,
    isMenuVisible: MutableState<Boolean>,
    result: UserModel?,
    resultAll: List<UserModel>
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
            UserCard(userModel = result, isMenuVisible = isMenuVisible)
        } else if (resultAll.isNotEmpty()) {
            userModels.forEach { user ->
                UserCard(userModel = user, isMenuVisible = isMenuVisible)
            }

        }


    }

}
