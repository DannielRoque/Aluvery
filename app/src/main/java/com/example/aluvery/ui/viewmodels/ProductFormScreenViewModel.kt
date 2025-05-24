package com.example.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.aluvery.ui.states.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductFormScreenViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(
        ProductFormUiState()
    )
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(

            )
        }
    }
}