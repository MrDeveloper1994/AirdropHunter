package com.scienpards.airdrophunter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.scienpards.airdrophunter.components.CustomRow
import com.scienpards.airdrophunter.components.DropTarget
import com.scienpards.airdrophunter.components.DropdownButton
import com.scienpards.airdrophunter.components.HiddenUserFindMenu
import com.scienpards.airdrophunter.dataManager.UserModel
import com.scienpards.airdrophunter.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


@Composable
fun UserFineScreen(navController: NavHostController, userModel: UserModel) {
    var searchResult by remember { mutableStateOf<User?>(null) }
    var searchResultAll by remember { mutableStateOf<List<User>>(emptyList()) }
    val users by userModel.users.collectAsState()
    var phone by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var showDialogNotFound by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var phoneValue by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var showProgress = remember { mutableStateOf(false) }
    val isMenuVisible = remember { mutableStateOf(false) }
    var user by remember { mutableStateOf<User?>(null) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            TextField(
                value = phoneValue,
                onValueChange = { newQuery ->
                    phoneValue = newQuery
                    phoneError = phoneValue.length !in 11..12
                },

                label = { Text("شماره موبایل") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 11.dp, end = 11.dp, top = 15.dp)
            )

            Spacer(modifier = Modifier.height(1.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = {
                        phone = if (phoneValue.startsWith("0")) phoneValue.drop(1) else phoneValue
                        val query = phone.toLongOrNull()

                        if (query != null) {
                            CoroutineScope(Dispatchers.IO).launch {

                                val user = userModel.findUserByPhone(query)
                                withContext(Dispatchers.Main) {
                                    if (user != null) {
                                        searchResult = user
                                        searchResultAll = emptyList()
                                    } else {
                                        showDialogNotFound = true
                                    }
                                }
                            }
                        } else {
                            showDialogNotFound = true
                        }
                    },
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraSmall),
                    shape = RectangleShape
                ) {
                    Text("جستجوی کاربر", maxLines = 1,style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp) )
                }
                DropdownButton(
                    onClick = {
                        keyboardController?.hide()
                        val phoneVal =
                            if (phoneValue.startsWith("0")) phoneValue.drop(1) else phoneValue

                        val query = phoneVal.toLongOrNull()
                        if (query != null) {
                            CoroutineScope(Dispatchers.IO).launch {
                                 user = userModel.findUserByPhone(query)
                                withContext(Dispatchers.Main) {
                                    if (user != null) {
                                        isMenuVisible.value =true
                                    } else {
                                        showDialogNotFound = true
                                    }
                                }
                            }
                        } else {
                            showDialogNotFound = true
                        }

                    },
                    isMenuVisible = isMenuVisible,
                )
                Button(
                    onClick = {
                        coroutineScope.launch {
                            showProgress.value = true
                            delay(Random.nextLong(5000, 6000))
                            showProgress.value = false
                            if (users.isNotEmpty()) {
                                searchResultAll = users
                                searchResult = null
                            } else {
                                showDialog = true
                            }
                        }
                    },
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraSmall),

                    shape = RectangleShape
                ) {
                    Text("همه کاربران", maxLines = 1,style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp) )
                }
            }
            Spacer(modifier = Modifier.height(3.dp))

            CustomRow(
                users = users,
                isMenuVisible = isMenuVisible,
                result = searchResult,
                resultAll = searchResultAll,
                modifier = Modifier.padding(3.dp),

                )
            HiddenUserFindMenu( isMenuVisible,user,showProgress)
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    text = { Text("! هنوز کاربری ثبت نام نشده است") },
                    confirmButton = {

                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .shadow(16.dp, shape = MaterialTheme.shapes.medium)
                        .clip(MaterialTheme.shapes.small)
                        .background(color = MaterialTheme.colorScheme.surface),

                    containerColor = MaterialTheme.colorScheme.surface
                )
                LaunchedEffect(Unit) {
                    delay(3000)
                    showDialog = false
                }
            }
            if (showDialogNotFound) {
                AlertDialog(
                    onDismissRequest = { showDialogNotFound = false },
                    text = { Text("!کاربر موردنظر پیدا نشد") },
                    confirmButton = {

                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .shadow(16.dp, shape = MaterialTheme.shapes.medium)
                        .clip(MaterialTheme.shapes.small)
                        .background(color = MaterialTheme.colorScheme.surface),
                    containerColor = MaterialTheme.colorScheme.surface
                )
                LaunchedEffect(Unit) {
                    delay(3000)
                    showDialogNotFound = false
                }
            }
            if (showProgress.value) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }





        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            DropTarget(
                onUserDeleted =
                {
                    searchResult = null
                }
            )
        }
    }

    if (showProgress.value) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

    }
}



