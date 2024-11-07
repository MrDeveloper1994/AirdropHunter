package com.scienpards.airdrophunter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.scienpards.airdrophunter.dataManager.UserModel
import com.scienpards.airdrophunter.models.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SignUpUserScreen(navController: NavHostController,userModel:UserModel) {
    var phoneError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf("") }
    var phoneInt by remember { mutableStateOf<Int?>(null) }
    var userId by remember { mutableStateOf("") }
    var userHash by remember { mutableStateOf("") }
    var notPixel by remember { mutableStateOf("") }
    var notPixelError by remember { mutableStateOf(false) }
    var userIdError by remember { mutableStateOf(false) }
    var userHashError by remember { mutableStateOf(false) }
    val scrollSurfaceState = rememberScrollState()
    var showPhoneError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showProgress by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

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
                value = phone,
                onValueChange = { newValue ->

                    if (newValue.all { it.isDigit() }) {
                        phone = newValue

                        // تبدیل رشته به عدد صحیح و بررسی طول
                        phoneInt = newValue.toIntOrNull()
                        phoneError = phone.length !in 11..12  // شرط برای طول
                    } else {
                        phoneError = true  // خطا برای ورودی غیرعدد
                    }
                },
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
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = notPixel,
                onValueChange = {
                    notPixel = it
                    notPixelError = notPixel.isEmpty()
                },
                label = { Text("Not Pixel Url") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (!phoneError && (notPixel.isNotEmpty() || (userId.isNotEmpty() && userHash.isNotEmpty()))) {
                        showProgress = true
                        coroutineScope.launch {
                            delay(Random.nextLong(4000, 7000))
                            showProgress = false
                            showDialog = true
                        }
                    }

                    /* Handle sign up logic here */
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
            title = { Text("ثبت‌نام موفقیت‌آمیز") },
            text = { Text("ثبت‌نام شما با موفقیت انجام شد!") },
            confirmButton = {
                TextButton(
                    onClick = {
                        val newUser = User(phone = phoneInt, userId = userId, userHash = userHash, notPixel = notPixel)
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
        )
    }
}
