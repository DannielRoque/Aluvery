package com.example.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aluvery.dao.ProductDao
import com.example.aluvery.model.Product
import com.example.aluvery.sampleData.sampleCandies
import com.example.aluvery.sampleData.sampleDrinks
import com.example.aluvery.ui.states.HomeScreenUiState

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

     var uiState : HomeScreenUiState by mutableStateOf(
         HomeScreenUiState(
             section = mapOf(
                 "Todos produtos" to dao.products(),
                 "Salgados" to sampleDrinks,
                 "Bebidas" to sampleCandies
             ),
         onSearchOnChange = {
             uiState = uiState.copy(
                 searchText = it,
                 searchedProducts = searchedProducts(it)
             )
         }
        )
     )
         private set


    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(text, ignoreCase = true) ||
                product.description?.contains(text, ignoreCase = true) ?: false
    }

    private fun searchedProducts(text: String): List<Product> =
        if (text.isNotBlank()) {
            sampleCandies.filter(containsInNameOrDescription(text)) +
                    dao.products().filter(containsInNameOrDescription(text)
            )
        } else emptyList()

}