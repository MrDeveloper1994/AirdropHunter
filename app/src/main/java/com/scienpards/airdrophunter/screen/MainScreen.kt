package com.scienpards.airdrophunter.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.scienpards.airdrophunter.AirdropHunterLogo
import com.scienpards.airdrophunter.components.CustomHoverButton
import com.scienpards.airdrophunter.components.HiddenLeftMenu
import com.scienpards.airdrophunter.components.RealTimeInternetSpeed

@Composable
fun MainScreen(navController: NavHostController, context: Context) {


    val scrollSurfaceState = rememberScrollState()
    val logoScrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        logoScrollState.scrollTo(logoScrollState.maxValue / 2)
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Surface(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .align(Alignment.Center)
                    .verticalScroll(scrollSurfaceState),
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(40.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(100.dp))
                    CustomHoverButton(
                        text = "ثبت نام کاربر",
                        onClick = { navController.navigate("signup") })
                    CustomHoverButton(
                        text = "مدیریت کاربران",
                        onClick = { navController.navigate("userManager") })
                    CustomHoverButton(
                        text = "لاگ مینی اپ ها",
                        onClick = { navController.navigate("miniApp") })
//                    CustomHoverButton(text = "Circle", onClick = { navController.navigate("circle") })
                    CustomHoverButton(
                        text = "اعلامیه ها ",
                        onClick = { navController.navigate("circle") })

                    Spacer(modifier = Modifier.height(60.dp))
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.TopCenter)

            ) {

                Column(Modifier.wrapContentSize()) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.secondary
                            )
                            .horizontalScroll(logoScrollState)
                    ) {
                        Spacer(modifier = Modifier.width(50.dp))
                        AirdropHunterLogo()
                        Spacer(modifier = Modifier.width(50.dp))
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier.fillMaxWidth().background(
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f)
                        )
                    ) {
                        Spacer(modifier = Modifier.width(50.dp))
                        RealTimeInternetSpeed(context)
                        Spacer(modifier = Modifier.width(50.dp))
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Default.Build,
                        modifier = Modifier.size(30.dp),
                        contentDescription = "Open"
                    )
                    VerticalDivider(
                        color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
                        thickness = 0.5.dp
                    )
                    Icon(
                        Icons.Default.Email,
                        modifier = Modifier.size(30.dp),
                        contentDescription = "Open mnu"
                    )
                    VerticalDivider(
                        color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
                        thickness = 0.5.dp
                    )
                    Icon(
                        Icons.Default.AccountBox,
                        modifier = Modifier.size(30.dp),
                        contentDescription = "Open men"
                    )
                    VerticalDivider(
                        color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
                        thickness = 0.5.dp
                    )
                    Icon(
                        Icons.Default.MailOutline,
                        modifier = Modifier.size(30.dp),
                        contentDescription = "Open enu"
                    )

                }
            }
            HiddenLeftMenu()


        }
    }
}




