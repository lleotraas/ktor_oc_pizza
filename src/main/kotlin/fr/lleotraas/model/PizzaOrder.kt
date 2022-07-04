package fr.lleotraas.model

import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
class PizzaOrder(
    val id: Int,
    val userId: Int,
    val restaurantName: String,
    val creationDate: String,
    val deliveryHour: String,
    val deliveryState: String,
    val isPaid: Boolean,
    val isDelivery: Boolean,
    val amount: Float
)