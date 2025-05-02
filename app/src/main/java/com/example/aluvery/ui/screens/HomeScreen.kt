package com.example.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.model.Product
import com.example.aluvery.sampleData.sampleCandies
import com.example.aluvery.sampleData.sampleDrinks
import com.example.aluvery.ui.components.CardProductItem
import com.example.aluvery.ui.components.ProductSection
import com.example.aluvery.ui.components.SearchTextField
import com.example.aluvery.ui.theme.AluveryTheme

class HomeScreenUiState(searchText: String = "") {

    var text by  mutableStateOf(searchText)

    val searchedProducts get() =
        sampleCandies.filter { product ->
            product.name.contains(text, ignoreCase = true) ||
                    product.description?.contains(text, ignoreCase = true) ?: false
        }

    fun isShowSections() : Boolean {
        return text.isBlank()
    }
}

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchText: String = ""
) {
    val state = remember {
        HomeScreenUiState(searchText)
    }
    val text = state.text
    val searchedProducts = remember(text) {
        state.searchedProducts
    }

    Column(Modifier.padding(16.dp)) {


        SearchTextField(searchText = text, onSearchChange = {
            state.text = it
        })


        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            if (state.isShowSections()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductSection(title = title, products = products)
                    }
                }
            } else {
                items(searchedProducts) { product ->
                    CardProductItem(product = product)
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(sectionalList)
}

val sectionalList: Map<String, List<Product>> = mapOf(
    "Promoções" to sampleCandies,
    "Salgados" to sampleDrinks,
    "Bebidas" to sampleCandies

)

@Preview
@Composable
private fun HomeScreenSearchTextPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(sections = sectionalList, searchText = "Loren")
        }
    }
}