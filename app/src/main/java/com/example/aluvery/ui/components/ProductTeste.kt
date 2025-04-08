package com.example.aluvery.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aluvery.R
import com.example.aluvery.ui.theme.Purple40
import com.example.aluvery.ui.theme.Purple400
import com.example.aluvery.ui.theme.Teal400

@Composable
private fun ProdutoTeste() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Purple400,
                                Teal400
                            )
                        )
                    )
                    .fillMaxHeight()
                    .width(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .offset(50.dp)
                        .border(
                            border = BorderStroke(
                                3.dp,
                                brush = Brush.verticalGradient(listOf(Purple40, Purple400))
                            ), shape = CircleShape
                        )
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )
            }

            Column(
                modifier = Modifier
                    .padding(30.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Spacer(modifier = Modifier.width(50.dp))
                Text(
                    text = LoremIpsum(words = 100).values.first(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 50.dp, end = 30.dp)
                )
            }
        }
    }
}