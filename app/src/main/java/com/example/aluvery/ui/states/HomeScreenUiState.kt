package com.example.aluvery.ui.states

import com.example.aluvery.model.Product

data class HomeScreenUiState(
    val section: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    var onSearchOnChange: (String) -> Unit = {}
) {

    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}