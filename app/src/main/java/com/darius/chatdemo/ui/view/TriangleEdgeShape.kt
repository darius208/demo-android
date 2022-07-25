package com.darius.chatdemo.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class TriangleEdgeShape(val offset: Int, val position: Boolean) : Shape {

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        if (position) {
            val trianglePath = Path().apply {
                moveTo(x = 0f, y = size.height - offset)
                lineTo(x = 0f, y = size.height)
                lineTo(x = 0f + offset, y = size.height)
            }
            return Outline.Generic(path = trianglePath)
        }
        val trianglePath = Path().apply {
            moveTo(x = 0f, y = size.height)

            lineTo(x = 20f, y = size.height - 20)
            lineTo(x = 20f + offset, y = size.height)
        }
        return Outline.Generic(path = trianglePath)
    }
}

@Preview(showBackground = true)
@Composable
fun TriangleReview() {
    Row(Modifier.height(IntrinsicSize.Max)) {
        Column(
            modifier = Modifier.background(
                color = Color.Blue,
                shape = TriangleEdgeShape(10, false)
            ).width(8.dp)
                .fillMaxHeight()
        ) {

        }
        Column(
            modifier = Modifier
                .background(
                    color = Color.Blue,
                    shape = RoundedCornerShape(4.dp, 4.dp, 4.dp, 0.dp)
                )
                .width(30.dp)
        ) {
            Text("Chat")
        }


    }
}