package fr.lleotraas.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.*

object DBPizzaOrderTable: Table<DBPizzaOrderEntity>("pizza_order") {
    val id = int("id").primaryKey().bindTo { it.id }
    val userId = int("user_id").bindTo { it.userId }
    val restaurantName = varchar("restaurant_name").bindTo { it.restaurantName }
    val creationDate = varchar("creation_date").bindTo { it.creationDate }
    val deliveryHour = varchar("delivery_hour").bindTo { it.deliveryHour }
    val deliveryState = varchar("delivery_state").bindTo { it.deliveryState }
    val isPaid = boolean("is_paid").bindTo { it.isPaid }
    val isDelivery = boolean("is_delivery").bindTo { it.isDelivery }
    val amount = float("amount").bindTo { it.amount }
}

interface DBPizzaOrderEntity: Entity<DBPizzaOrderEntity> {
    val id: Int
    val userId: Int
    val restaurantName: String
    val creationDate: String
    val deliveryHour: String
    val deliveryState: String
    val isPaid: Boolean
    val isDelivery: Boolean
    val amount: Float
}