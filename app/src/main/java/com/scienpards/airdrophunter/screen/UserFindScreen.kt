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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.scienpards.airdrophunter.components.DropTarget
import com.scienpards.airdrophunter.components.UserCard
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
    var showProgress by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
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

            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = {
                        phone = if (phoneValue.startsWith("0")) phoneValue.drop(1) else phoneValue
                        val query = phone.toLongOrNull()

                        if (query != null) {
                            CoroutineScope(Dispatchers.IO).launch {

                                println("Search Query: $query")
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
                        .clip(MaterialTheme.shapes.extraSmall)
                        .width(160.dp)
                        .wrapContentHeight(),
                    shape = RectangleShape
                ) {
                    Text("search user")
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            showProgress = true
                            delay(Random.nextLong(5000, 6000))
                            showProgress = false
                            if (users.isNotEmpty()) {
                                searchResultAll = users
                                searchResult = null
                            } else {
                                showDialog = true
                            }
                        }
                    },
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraSmall)
                        .width(160.dp)
                        .wrapContentHeight(),
                    shape = RectangleShape
                ) {
                    Text("َAll user")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
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

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (searchResult != null) {
                    items(listOf(searchResult!!)) { user ->
                            UserCard(user = user)
                        }

                } else if (searchResultAll.isNotEmpty()) {
                    items(users) { user ->
                        UserCard(user = user)
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            DropTarget(
                onUserDeleted =
                {
                    searchResult = null
                }
            )
        }

    }

    if (showProgress) {

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



