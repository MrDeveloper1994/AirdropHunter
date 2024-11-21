package com.scienpards.airdrophunter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scienpards.airdrophunter.components.CustomDrawerMenu
import com.scienpards.airdrophunter.components.CustomNumberPickerWithArrow
import com.scienpards.airdrophunter.models.Log
import com.scienpards.airdrophunter.models.LogFilter
import com.scienpards.airdrophunter.utils.convertToTehranTime
import com.scienpards.airdrophunter.viewModel.LogViewModel


@Composable
fun GoatsScreen(logViewModel: LogViewModel) {

    val logs by logViewModel.logs.collectAsState()
    var logFilter by remember { mutableStateOf(LogFilter()) }
    var number by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .wrapContentHeight()
                .wrapContentWidth(),
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 12.dp
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    CustomDrawerMenu(
                        listOf("همه", "Goats", "Circle"),
                        "مینی اپ ها",
                        onOptionSelect = { option ->
                            when (option) {
                                "همه" -> logFilter.endpoint = null
                                "Goats" -> {
                                    logFilter.endpoint = "Goats"
                                }

                                "Circle" -> {
                                    logFilter.endpoint = "Circle"
                                }
                            }
                        })

                    CustomDrawerMenu(
                        listOf("همه", "200", "401", "400"),
                        "وضعیت شبکه",
                        onOptionSelect = { option ->
                            when (option) {
                                "همه" -> logFilter.statusCode = null
                                "200" -> {
                                    logFilter.statusCode = 200
                                }

                                "401" -> {
                                    logFilter.statusCode = 401
                                }

                                "400" -> {
                                    logFilter.statusCode = 400
                                }
                            }
                        })
                }

                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),

                    horizontalArrangement = Arrangement.SpaceAround,

                    ) {
                    Button(
                        modifier = Modifier
                            .height(45.dp)
                            .shadow(16.dp, ambientColor = Color.Black),
                        onClick = { logFilter = LogFilter() },
                        shape = RectangleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            "پاک کردن",
                            color = Color.White,
                            minLines = 1,
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp)
                        )
                    }

                    CustomNumberPickerWithArrow(onNumberChanged = { numberPicker ->
                        when (numberPicker) {
                            0 -> logFilter.timeStampResponce = null
                            1 -> {
                                val currentTime = System.currentTimeMillis()
                                logFilter.timeStampResponce = currentTime - (86400000)

                            }

                            2 -> {
                                val currentTime = System.currentTimeMillis()
                                logFilter.timeStampResponce = currentTime - (172800000)
                            }
                        }
                    })

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),

                    horizontalArrangement = Arrangement.SpaceAround,

                    ) {

                    TextField(
                        value = number,
                        onValueChange = { input ->
                            if (input.length <= 10) {
                                if (input.isEmpty() || input.firstOrNull() != '0') {
                                    number = input
                                    if (number.length == 10) {
                                        logFilter.phoneNumber = number
                                    }
                                }
                            }

                        },
                        colors = TextFieldDefaults.colors(unfocusedTextColor = MaterialTheme.colorScheme.primary),
                        label = {
                            Text(
                                "شماره تلفن",
                                style = TextStyle(fontSize = 12.sp)
                            )
                        },
                        textStyle = TextStyle(fontSize = 14.sp), singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .width(250.dp)
                            .padding(0.dp)
                            .height(50.dp)
                            .shadow(16.dp, ambientColor = Color.Black)
                            .border(5.dp, color = MaterialTheme.colorScheme.primary)
                            .background(color = MaterialTheme.colorScheme.primary),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
//                .padding(8.dp)
                .background(color = Color.DarkGray)
        ) {
            val filteredLogs = logs.filter { log ->
                (logFilter.endpoint == null || log.endpoint.contains(
                    logFilter.endpoint!!,
                    ignoreCase = true
                )) &&
                        (logFilter.statusCode == null || log.statusCode == logFilter.statusCode) &&
                        (logFilter.phoneNumber == null || log.phoneNumber == logFilter.phoneNumber!!.toLong()) &&
                        (logFilter.timeStampResponce == null || log.timestampResponse >= logFilter.timeStampResponce!!.toLong())
            }
            items(filteredLogs) { log ->
                LogItem(log = log)
            }
        }
    }
}

@Composable
fun LogItem(log: Log) {

    Text(
        text = "${log.endpoint}||${log.statusCode}||${log.statusMessage}||${log.phoneNumber}||${
            convertToTehranTime(
                log.timestampResponse
            )
        }||${convertToTehranTime(log.timestampRequest)}",
        color = if (log.statusCode == 200) Color.Green else if (log.statusCode == 400) Color.Yellow else Color.Red,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
