package com.example.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.aluvery.ui.screens.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductFormScreenViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(
        ProductFormUiState()
    )
    val uiState get() = _uiState.asStateFlow()

}