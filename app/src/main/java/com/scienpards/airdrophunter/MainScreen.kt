package com.scienpards.airdrophunter

import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.scienpards.airdrophunter.components.CustomHoverButton
import androidx.compose.material3.MaterialTheme

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AirdropHunterLogo(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 25.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .wrapContentHeight()
                .wrapContentWidth(),
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(40.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomHoverButton(text = "ثبت نام کاربر ", onClick = {})
                CustomHoverButton(text = "حذف کاربر", onClick = {})
                CustomHoverButton(text = "اطلاعات کاربر ", onClick = {})
                CustomHoverButton(text = "Not Pixel", onClick = {})
                CustomHoverButton(text = "Circle", onClick = {})
                CustomHoverButton(text = "Major", onClick = {})
                CustomHoverButton(text = "Blum", onClick = {})
            }
        }
    }
}



