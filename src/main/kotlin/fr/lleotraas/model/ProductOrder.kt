package fr.lleotraas.model

import kotlinx.serialization.Serializable

@Serializable
class ProductOrder(
    val productName: String,
    val orderId: Int,
    val vatRate: Float,
    val quantity: Int,
    val unitPriceDf: Float
)