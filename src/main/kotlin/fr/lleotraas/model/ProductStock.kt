package fr.lleotraas.model

import kotlinx.serialization.Serializable

@Serializable
class ProductStock(
    val productName: String,
    val stockName: String
)