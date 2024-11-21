package com.scienpards.airdrophunter.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.scienpards.airdrophunter.viewModel.UserViewModel
import com.scienpards.airdrophunter.models.UserModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun HiddenUserFindMenu(
    isMenuVisible: MutableState<Boolean>,
    userModel: UserModel?,
    showProgress: MutableState<Boolean>
) {
    println("user2 ${userModel?.phone}")

    var phoneError by remember { mutableStateOf(false) }
    var phone by rememberSaveable { mutableStateOf("") }
    var phoneValue by remember { mutableStateOf("") }
    var userId by rememberSaveable { mutableStateOf("") }
    var userHash by rememberSaveable { mutableStateOf("") }
    var notPixel by rememberSaveable { mutableStateOf("") }
    var notPixelError by remember { mutableStateOf(false) }
    var userIdError by remember { mutableStateOf(false) }
    var userHashError by remember { mutableStateOf(false) }
    var showPhoneError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showFindUser by remember { mutableStateOf(false) }
    val scrollMenu = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val userViewModel: UserViewModel = hiltViewModel()

    val animatedOffset = animateDpAsState(
        targetValue = if (isMenuVisible.value) (0).dp else (250).dp,
        animationSpec = tween(durationMillis = 1500, easing = LinearOutSlowInEasing), label = "menu"
    )
    LaunchedEffect(userModel?.phone) {
        phoneValue = "${(userModel?.phone) ?: ""}"
    }
    LaunchedEffect(userModel?.userId) {
        userId = "${(userModel?.userId) ?: ""}"
    }
    LaunchedEffect(userModel?.userHash) {
        userHash = "${(userModel?.userHash) ?: ""}"
    }
    LaunchedEffect(userModel?.goats) {
        notPixel = "${(userModel?.goats) ?: ""}"
    }
    Column (
        modifier = Modifier
            .clip(shape = RectangleShape)
            .height(280.dp)
            .offset(y = animatedOffset.value)
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .verticalScroll(scrollMenu),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            HorizontalDivider(color = MaterialTheme.colorScheme.onSecondary, thickness = 1.dp)

            TextField(
                value = phoneValue,
                onValueChange = { newValue ->

                    phoneValue = newValue
                    phoneError = phoneValue.length !in 11..12


                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                label = {
                    Text(
                        text = "phone number",
                        style = TextStyle(fontSize = 12.sp)
                    )
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp),
                modifier = Modifier
                    .fillMaxWidth()

                    .height(60.dp)
                    .padding(5.dp)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            showPhoneError = phoneError
                        }
                    },
                isError = phoneError && showPhoneError

            )
            if (phoneError && showPhoneError) {
                Text(
                    text = "شماره تلفن صحیح نمی باشد",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            TextField(
                value = userId,
                onValueChange = {
                    userId = it
                    userIdError = userId.isEmpty()
                },
                label = {
                    Text(
                        "User Id",
                        style = TextStyle(fontSize = 12.sp)
                    )
                }, textStyle = TextStyle(fontSize = 14.sp), singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )

            TextField(
                value = userHash,
                onValueChange = {
                    userHash = it
                    userHashError = userHash.isEmpty()

                },
                label = {
                    Text(
                        "User Hash",
                        style = TextStyle(fontSize = 12.sp)
                    )
                }, textStyle = TextStyle(fontSize = 14.sp), singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            TextField(
                value = notPixel,
                onValueChange = {
                    notPixel = it
                    notPixelError = notPixel.isEmpty()
                },
                label = {
                    Text(
                        "Not Pixel Url",
                        style = TextStyle(fontSize = 12.sp)
                    )
                }, textStyle = TextStyle(fontSize = 14.sp), singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Button(
                onClick = {
                    if (!phoneError && (notPixel.isNotEmpty() || (userId.isNotEmpty() && userHash.isNotEmpty()))) {

                        coroutineScope.launch {
                            showProgress.value = true
                            delay(Random.nextLong(3000, 5000))
                            showProgress.value = false
                            phone =
                                if (phoneValue.startsWith("0")) phoneValue.drop(1) else phoneValue
                            showDialog = true

                        }
                    }


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                enabled = !phoneError && (notPixel.isNotEmpty() || (userId.isNotEmpty() && userHash.isNotEmpty()))
            ) {
                Text(text = " آپدیت کاربر")
            }

            Spacer(modifier = Modifier.height(100.dp))

        }


    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text("!آپدیت کاربر با موفقیت انجام شد") },
            confirmButton = {
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            userViewModel.updateUserByPhone(
                                phone.toLong(),
                                userId = userId,
                                userHash = userHash,
                                notPixel = notPixel
                            )

                            showDialog = false
                            isMenuVisible.value = false
                            scrollMenu.scrollTo(0)
                        }

                    }
                ) {
                    Text("باشه")
                }
            },
            modifier = Modifier
                .padding(16.dp)
                .shadow(16.dp, shape = MaterialTheme.shapes.medium)
                .clip(MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colorScheme.surface)
        )
    }


}