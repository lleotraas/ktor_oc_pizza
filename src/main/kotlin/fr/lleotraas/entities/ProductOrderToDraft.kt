package fr.lleotraas.entities

class ProductOrderToDraft(
    val productName: String,
    val orderId: Int,
    val vatRate: Float,
    val quantity: Int,
    val unitPriceDf: Float
)