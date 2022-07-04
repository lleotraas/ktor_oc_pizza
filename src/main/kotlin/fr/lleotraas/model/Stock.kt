package fr.lleotraas.model

import kotlinx.serialization.Serializable

@Serializable
class Stock(
    val name: String,
    val remainingQuantity: Int
)