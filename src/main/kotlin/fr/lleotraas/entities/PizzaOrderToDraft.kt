package fr.lleotraas.entities

class PizzaOrderToDraft(
    val userId: Int,
    val restaurantName: String,
    val creationDate: String,
    val deliveryHour: String,
    val deliveryState: String,
    val isPaid: Boolean,
    val isDelivery: Boolean,
    val amount: Float
)