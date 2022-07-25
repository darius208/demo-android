package com.darius.chatdemo.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darius.chatdemo.R

@Composable
fun Conversation() {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Box(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()) {
        Conversation(SampleData.conversationSample)
        Row(modifier = Modifier.align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .weight(0.5f)
                    .height(IntrinsicSize.Min)) {
                Button(onClick = { /*TODO*/ }, Modifier.fillMaxHeight()) {
                    Text("+")
                }
            }
            Box(
                Modifier
                    .weight(4.5f)
                    .height(IntrinsicSize.Min)) {
                TextField(
                    value = text ,
                    onValueChange = {newText->
                        text = newText
                    },
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth())
            }
            Box(
                Modifier
                    .weight(1f)
                    .height(IntrinsicSize.Min)) {
                Button(onClick = { text = TextFieldValue("") }, Modifier.fillMaxHeight()) {
                    Text("send", textAlign = TextAlign.Center)
                }
            }


        }
    }
}

@Composable
fun MessageCard(msg: TextMessage) {
    Row(modifier = Modifier
        .padding(all = 8.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = if (msg.direction == "Incoming") Arrangement.Start else Arrangement.End) {
        if (msg.direction == "Incoming") {
            Image(
                painter = painterResource(id = R.drawable.icybay),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        Column(horizontalAlignment = if (msg.direction == "Incoming") Alignment.Start else Alignment.End) {
            Text(
                text = msg.senderId,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Surface(
                shape = MaterialTheme.shapes.medium, elevation = 1.dp
            ) {

            }
            Text(
                text = msg.message,
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body2

            )
        }
    }
}

@Composable
fun Conversation(messages: List<Any>) {
    val listState = rememberLazyListState()
    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size)
    }
    LazyColumn(state = listState){
        items(messages) { message ->
            if(message is TextMessage) {
                MessageCard(message)
            }

        }
    }
}
data class TextMessage(
    val id: Int,
    val direction: String,
    val timestamp: Int,
    val senderId: String,
    val destinationID: String,
    val message: String,
    val deliveryStatus: String,
    val deliveryTimestamp: Int
)

data class ImageMessage(
    val id: Int,
    val direction: String,
    val timestamp: Int,
    val senderId: String,
    val destinationID: String,
    val imageURL: String,
    val thumbnailURL: String,
    val deliveryStatus: String,
    val deliveryTimestamp: Int
)


@Preview(showBackground = true)
@Composable
fun DefaultReview() {
    Conversation()
}

object SampleData {
    val conversationSample = listOf(
        TextMessage(
            1,
            "Incoming",
            1589463871,
            "Bob",
            "Sally",
            "I was sent this message",
            "Delivered",
            1589463871
        ),
        TextMessage(
            3,
            "Outbound",
            1589467569,
            "Bob",
            "Sally",
            "I sent this message",
            "Delivered",
            1589467569
        ),
        ImageMessage(
            3,
            "Incoming",
            1589485467,
            "Bob",
            "Sally",
            "turtlerock",
            "https://thumbnail.image.url",
            "Delivered",
            1589485467
        )
    )
}
