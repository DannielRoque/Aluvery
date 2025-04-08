package com.example.aluvery.extension

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale


fun BigDecimal.converterCurancyBrazilian(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
}