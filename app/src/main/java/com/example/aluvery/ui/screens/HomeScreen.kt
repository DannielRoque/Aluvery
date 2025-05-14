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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.model.Product
import com.example.aluvery.sampleData.sampleCandies
import com.example.aluvery.sampleData.sampleDrinks
import com.example.aluvery.sampleData.sampleSections
import com.example.aluvery.ui.components.CardProductItem
import com.example.aluvery.ui.components.ProductSection
import com.example.aluvery.ui.components.SearchTextField
import com.example.aluvery.ui.theme.AluveryTheme

class HomeScreenUiState(
    val section: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    var onSearchOnChange: (String) -> Unit = {}
) {

    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}

@Composable
fun HomeScreen(products: List<Product>) {
    val sections = mapOf(
        "Todos produtos" to products,
        "Salgados" to sampleDrinks,
        "Bebidas" to sampleCandies
    )
    var text by rememberSaveable {
        mutableStateOf("")
    }

    fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(text, ignoreCase = true) ||
                product.description?.contains(text, ignoreCase = true) ?: false
    }

    val searchedProducts = remember(text, products) {
        if (text.isNotBlank()) {
            sampleCandies.filter(containsInNameOrDescription()) + products.filter(
                containsInNameOrDescription()
            )
        } else emptyList()
    }

    val state = remember(products, text) {
        HomeScreenUiState(
            section = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchOnChange = {
                text = it
            }
        )
    }
    HomeScreen(state = state)
}


@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {
    val sections = state.section
    val text = state.searchText
    val searchedProducts = state.searchedProducts

    Column(Modifier.padding(16.dp)) {

        SearchTextField(searchText = text, onSearchChange = state.onSearchOnChange)

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
    HomeScreen(HomeScreenUiState(section = sampleSections))
}

@Preview
@Composable
private fun HomeScreenSearchTextPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(state = HomeScreenUiState(searchText = "Loren", section = sampleSections))
        }
    }
}