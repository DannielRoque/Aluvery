package com.example.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aluvery.dao.ProductDao
import com.example.aluvery.model.Product
import com.example.aluvery.sampleData.sampleCandies
import com.example.aluvery.sampleData.sampleDrinks
import com.example.aluvery.ui.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(
        HomeScreenUiState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchOnChange = {
                    _uiState.value = _uiState.value.copy(
                        searchText = it,
                        searchedProducts = searchedProducts(it)
                    )
                }
            )
        }

        viewModelScope.launch {
            dao.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    section = mapOf(
                        "Todos produtos" to products,
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    ),
                    searchedProducts = searchedProducts(_uiState.value.searchText)
                )
            }
        }
    }


    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(text, ignoreCase = true) ||
                product.description?.contains(text, ignoreCase = true) ?: false
    }

    private fun searchedProducts(text: String): List<Product> =
        if (text.isNotBlank()) {
            sampleCandies.filter(containsInNameOrDescription(text)) +
                    dao.products().value.filter(
                        containsInNameOrDescription(text)
                    )
        } else emptyList()

}