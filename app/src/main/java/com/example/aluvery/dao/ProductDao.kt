package com.example.aluvery.dao

import com.example.aluvery.sampleData.sampleCandies

class ProductDao {

    companion object {
        private val products = sampleCandies.toMutableList()
    }

    fun products() = products.toList()
}