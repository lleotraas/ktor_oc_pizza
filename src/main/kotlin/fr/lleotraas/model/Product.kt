package fr.lleotraas.model

import kotlinx.serialization.Serializable

@Serializable
class Product(
    val name: String,
    val unitPriceDf: Float,
    val description: String,
    val vatRate: Float
)