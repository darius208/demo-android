package com.darius.chatdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.darius.chatdemo.ui.view.Conversation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Conversation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}
