package com.example.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aluvery.R
import com.example.aluvery.ui.theme.Purple400
import com.example.aluvery.ui.theme.Teal400

@Composable
fun ProductItemDescriptionTeste(description: String = "") {
    Surface(
        modifier = Modifier
            .shadow(4.dp, shape = RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .heightIn(250.dp, 260.dp)
                .width(200.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            val imageSize = 100.dp
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Purple400,
                                Teal400
                            )
                        )
                    )
                    .fillMaxWidth()
                    .height(imageSize)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Imagem do Produto",
                    modifier = Modifier
                        .size(imageSize)
                        .offset(y = imageSize / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter)
                )
            }
            Column {
                Spacer(modifier = Modifier.height(imageSize / 2))
                Text(
                    text = LoremIpsum(50).values.first(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                )
                Text(
                    text = "R$ 14,99",
                    modifier = Modifier.padding(
                        top = 8.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 12.dp
                    ),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )

                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(16.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductItemDescriptionTestPreview() {
    ProductItemDescriptionTeste(LoremIpsum(words = 20).values.first())
    ProductItemDescriptionTeste()
    ProductItemDescriptionTeste(LoremIpsum(words = 20).values.first())
}