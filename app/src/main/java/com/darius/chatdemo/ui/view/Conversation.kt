package com.darius.chatdemo.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darius.chatdemo.R
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun Conversation() {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var listMessage by remember {
        mutableStateOf(SampleData.conversationSample)
    }
        Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxSize()) {
            Conversation(listMessage, modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                    Button(onClick = {
                        val date = Date()
                        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
                        val df = SimpleDateFormat("yyyy.MM.dd HH:mm")
                        val newList = ArrayList(listMessage)
                        println(df.parse(format.format(date)).time)
                        if (text.text.length > 0) {
                            newList.add(TextMessage(
                                listMessage.size+1,
                                "Outbound",
                                df.parse(format.format(date)).time,
                                "Bob",
                                "Sally",
                                text.text,
                                "Delivered",
                                df.parse(format.format(date)).time
                            ))
                            println("sdfasfasdfasdf")
                            listMessage = newList
                        }
                        text = TextFieldValue("") },
                        Modifier.fillMaxHeight()) {
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
        verticalAlignment = Alignment.CenterVertically,
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
        Row(Modifier.height(IntrinsicSize.Max)) {
            if (msg.direction == "Incoming") {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = TriangleEdgeShape(20, false)
                        )
                        .width(8.dp)
                        .fillMaxHeight()
                ) {

                }
            }
            Column(horizontalAlignment = if (msg.direction == "Incoming") Alignment.Start else Alignment.End,
                modifier = Modifier.background(
                    color = if (msg.direction == "Incoming") Color.LightGray else Color.Cyan,
                    shape = if (msg.direction == "Outbound") RoundedCornerShape(6.dp, 6.dp, 0.dp, 6.dp) else RoundedCornerShape(6.dp, 6.dp, 6.dp, 0.dp))) {
                Text(
                    text = msg.message,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2

                )
            }
            if (msg.direction == "Outbound"){
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.Cyan,
                            shape = TriangleEdgeShape(20, true)
                        )
                        .width(8.dp)
                        .fillMaxHeight()
                ) {

                }
            }

        }


    }
}

@Composable
fun ImageCard(msg: ImageMessage) {
    val image: Painter = painterResource(id = msg.imageURL)
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
        Row(Modifier.height(IntrinsicSize.Max),
            verticalAlignment = Alignment.Bottom) {
            if (msg.direction == "Incoming") {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = TriangleEdgeShape(20, false)
                        )
                        .width(8.dp)
                        .fillMaxHeight()
                ) {

                }
            }
            Column(horizontalAlignment = if (msg.direction == "Incoming") Alignment.Start else Alignment.End,
                modifier = Modifier
                    .background(
                        color = if (msg.direction == "Incoming") Color.LightGray else Color.Cyan,
                        shape = if (msg.direction == "Outbound") RoundedCornerShape(
                            6.dp,
                            6.dp,
                            0.dp,
                            6.dp
                        ) else RoundedCornerShape(6.dp, 6.dp, 6.dp, 0.dp)
                    )
                    .padding(10.dp)) {
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier.size(200.dp)
                )
            }
            if (msg.direction == "Outbound"){
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.Cyan,
                            shape = TriangleEdgeShape(20, true)
                        )
                        .width(8.dp)
                        .fillMaxHeight()
                ) {

                }
            }

        }


    }
}

@Composable
fun Conversation(messages: List<Any>, modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size)
    }
    Box(modifier = modifier,
        contentAlignment = Alignment.BottomEnd) {
        LazyColumn(state = listState){
            items(messages) { message ->
                if(message is TextMessage) {
                    MessageCard(message)
                }
                if (message is ImageMessage) {
                    ImageCard(message)
                }

            }
        }
    }

}
data class TextMessage(
    val id: Int,
    val direction: String,
    val timestamp: Long,
    val senderId: String,
    val destinationID: String,
    val message: String,
    val deliveryStatus: String,
    val deliveryTimestamp: Long
)

data class ImageMessage(
    val id: Int,
    val direction: String,
    val timestamp: Long,
    val senderId: String,
    val destinationID: String,
    val imageURL: Int,
    val thumbnailURL: String,
    val deliveryStatus: String,
    val deliveryTimestamp: Long
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
            4,
            "Incoming",
            1589485467,
            "Bob",
            "Sally",
            R.drawable.icybay,
            "https://thumbnail.image.url",
            "Delivered",
            1589485467
        ),
        ImageMessage(
            4,
            "Outbound",
            1589485467,
            "Bob",
            "Sally",
            R.drawable.twinlake,
            "https://thumbnail.image.url",
            "Delivered",
            1589485467
        )
    )
}
