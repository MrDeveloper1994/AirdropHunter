package com.scienpards.airdrophunter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.scienpards.airdrophunter.dataManager.UserModel
import com.scienpards.airdrophunter.models.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SignUpUserScreen(navController: NavHostController, userModel: UserModel) {
    var phoneError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf("") }
    var phoneValue by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    var userHash by remember { mutableStateOf("") }
    var notPixel by remember { mutableStateOf("") }
    var notPixelError by remember { mutableStateOf(false) }
    var userIdError by remember { mutableStateOf(false) }
    var userHashError by remember { mutableStateOf(false) }
    val scrollSurfaceState = rememberScrollState()
    var showPhoneError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showFindUser by remember { mutableStateOf(false) }
    var showProgress by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
//    LaunchedEffect(Unit) {
//        val exitedUser = userModel.findUserByPhone(phone)
//
//        }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollSurfaceState)
            .padding(16.dp),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "فرم ثبت کاربر", style = MaterialTheme.typography.titleSmall)

            TextField(
                value = phoneValue,
                onValueChange = { newValue ->

                    phoneValue = newValue
                    phoneError = phoneValue.length !in 11..12


                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                label = { Text("phone number") },
                modifier = Modifier
                    .fillMaxWidth()
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
                label = { Text("User Id") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = userHash,
                onValueChange = {
                    userHash = it
                    userHashError = userHash.isEmpty()

                },
                label = { Text("User Hash") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = notPixel,
                onValueChange = {
                    notPixel = it
                    notPixelError = notPixel.isEmpty()
                },
                label = { Text("Not Pixel Url") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (!phoneError && (notPixel.isNotEmpty() || (userId.isNotEmpty() && userHash.isNotEmpty()))) {
                        showProgress = true
                        coroutineScope.launch {
                            delay(Random.nextLong(4000, 7000))
                            showProgress = false
                            phone =
                                if (phoneValue.startsWith("0")) phoneValue.drop(1) else phoneValue
                            val existingUser = userModel.findUserByPhone(phone.toLong())
                            showProgress = false
                            if (existingUser == null) {
                                showDialog = true

                            } else {
                                showFindUser = true
                            }
                        }
                    }


                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !phoneError && (notPixel.isNotEmpty() || (userId.isNotEmpty() && userHash.isNotEmpty()))
            ) {
                Text(text = "Sign Up")
            }
        }
    }
    if (showProgress) {
//
//        LaunchedEffect(Unit) {
//            kotlinx.coroutines.delay(Random.nextLong(4000, 7000))
//            showProgress = false
//            showDialog = true
//        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text("!ثبت‌نام شما با موفقیت انجام شد") },
            confirmButton = {
                TextButton(
                    onClick = {

                        val newUser = User(
                            phone = phone.toLongOrNull(),
                            userId = userId,
                            userHash = userHash,
                            notPixel = notPixel
                        )

                        userModel.addUser(newUser)
                        navController.navigate("home") {
                            popUpTo("signup") { inclusive = true }
                        }
                        showDialog = false

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
    if (showFindUser) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text("!کاربر موردنظر قبلا ثبت نام شده است") },
            confirmButton = {

            },
            modifier = Modifier
                .padding(3.dp)
                .shadow(16.dp, shape = MaterialTheme.shapes.medium)
                .clip(MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colorScheme.surface)
        )
        LaunchedEffect(Unit) {
            delay(3000)
            showFindUser = false
        }
    }

}
