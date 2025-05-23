package com.example.aluvery.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.aluvery.R
import com.example.aluvery.model.Product
import com.example.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormUiState(
    val url: String = String(),
    val name: String = String(),
    val price: String = String(),
    val description: String = String(),
    val isShowPreview: Boolean = url.isNotBlank(),
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onSaveClick: () -> Unit = {}
)

@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit = {}
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var url by rememberSaveable {
        mutableStateOf("")
    }
    var price by rememberSaveable {
        mutableStateOf("")
    }
    var description by rememberSaveable {
        mutableStateOf("")
    }
    val formatter = remember {
        DecimalFormat("#.##")
    }

    ProductFormScreen(
        state = ProductFormUiState(
            url = url,
            name = name,
            price = price,
            description = description,
            onNameChange = {
                name = it
            },
            onUrlChange = {
                url = it
            },
            onPriceChange = {
                try {
                    price = formatter.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        price = it
                    }
                }
            },
             onDescriptionChange = {
                description = it
            },
            onSaveClick = {
                val convertedPrice = try {
                    BigDecimal(price)
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }
                val product = Product(
                    name = name,
                    image = url,
                    price = convertedPrice,
                    description = description
                )
                Log.i("ProductFormActivity", "ProductFormScreen: $product")
                onSaveClick(product)
            }
        )
    )
}


@Composable
fun ProductFormScreen(
    state: ProductFormUiState = ProductFormUiState()
) {
    val url = state.url
    val name = state.name
    val price = state.price
    val description = state.description

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier)
        Text(text = "Criando o Produto", Modifier.fillMaxWidth(), fontSize = 28.sp)


        if (state.isShowPreview) {
            AsyncImage(
                model = url,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop
            )
        }
        TextField(
            value = url,
            onValueChange = state.onUrlChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Url da Imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )
        TextField(
            value = name, onValueChange = state.onNameChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )
        TextField(
            value = price,
            onValueChange = state.onPriceChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Preço")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = description,
            onValueChange = state.onDescriptionChange,
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text(text = "Descrição")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Button(
            onClick = state.onSaveClick,
            Modifier.fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }
        Spacer(modifier = Modifier)
    }
}

    @Preview
    @Composable
    fun ProducFormScreenPreview(modifier: Modifier = Modifier) {
        AluveryTheme {
            Surface {
                ProductFormScreen(state = ProductFormUiState())
            }
        }
    }
@Preview
@Composable
fun ProductFormScreenFilledPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen(
                state = ProductFormUiState(
                    url = "url teste",
                    name = "daniel teste",
                    price = "123",
                    description = "descrição teste"
                )
            )
        }
    }
}