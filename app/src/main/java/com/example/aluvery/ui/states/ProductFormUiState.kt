package com.example.aluvery.ui.states

data class ProductFormUiState(
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