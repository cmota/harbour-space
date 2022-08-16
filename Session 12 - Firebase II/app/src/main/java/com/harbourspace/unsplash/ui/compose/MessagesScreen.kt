package com.harbourspace.unsplash.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.harbourspace.unsplash.R
import com.harbourspace.unsplash.model.Message
import com.harbourspace.unsplash.ui.compose.theme.typography

@Composable
fun AddMessagesScreen(
    messages: List<Message>,
    onSendMessage: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        LazyColumn {
            items(messages) {
                val self = Firebase.auth.currentUser?.email == it.username

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = if(self) Alignment.End else Alignment.Start
                ) {

                    Card(
                        modifier = Modifier
                            .clip(RoundedCornerShape(25.dp))
                            .border(2.dp, MaterialTheme.colors.onPrimary, RoundedCornerShape(25.dp)),
                        backgroundColor = if(self) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary
                    ) {

                        Column(
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            )
                        ) {

                            Text(
                                text = it.content,
                                style = typography.subtitle1,
                                color = if(self) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
                            )

                            Text(
                                text = it.username,
                                style = typography.subtitle2,
                                color = if(self) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val message = remember { mutableStateOf("") }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f)
            ) {
                OutlinedTextField(
                    value = message.value,
                    onValueChange = { value ->
                        message.value = value
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.messages_text_hint),
                            style = typography.h1,
                            maxLines = 1
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions {
                        onSendMessage(message.value)
                        message.value = ""
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.LightGray
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f)
                    .clickable {
                        onSendMessage(message.value)
                        message.value = ""
                    }
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                val image = painterResource(id = R.drawable.ic_send)
                val description = stringResource(R.string.description_send_message)

                Image(
                    painter = image,
                    contentDescription = description,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}