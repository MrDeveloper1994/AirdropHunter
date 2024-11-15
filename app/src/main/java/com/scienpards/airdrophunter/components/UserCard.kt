package com.scienpards.airdrophunter.components

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scienpards.airdrophunter.models.User


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserCard(user: User) {
    val scrollSurfaceState = rememberScrollState()

    Column(modifier = Modifier
        .wrapContentSize()
        .dragAndDropSource {
            detectTapGestures(onLongPress = {

                startTransfer(
                    DragAndDropTransferData(
                        clipData = ClipData.newPlainText(
                            "user_data",
                            "${user.userId},${user.phone},${user.userHash},${user.notPixel}"
                        ),


                        )
                )
            })
        }) {
        Card(
            modifier = Modifier
                .size(200.dp, 150.dp)
                .verticalScroll(scrollSurfaceState),
            colors = CardColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                disabledContentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = RectangleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 0.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        ) {
                            append("Phone : ")
                        }
                        append(user.phone.toString())
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        ) {
                            append("UserId : ")
                        }
                        append(user.userId.toString())
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        ) {
                            append("userHash : ")
                        }
                        append(user.userHash.toString())
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        ) {
                            append("notPixel : ")
                        }
                        append(user.notPixel.toString())
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

            }

        }
        DropdownButton(onClick = {
            println("Button clicked!")
        })
    }

}


@Composable
fun DropdownButton(onClick: () -> Unit) {
    Button(
        onClick = onClick, modifier = Modifier
            .padding(0.dp)
            .wrapContentHeight()
            .width(200.dp), shape = RectangleShape, colors = ButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = "Edit")
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}

