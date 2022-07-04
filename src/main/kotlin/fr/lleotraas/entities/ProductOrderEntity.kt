package fr.lleotraas.entities

import fr.lleotraas.entities.DBProductTable.bindTo
import fr.lleotraas.entities.DBProductTable.primaryKey
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.float
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBProductOrderTable: Table<DBProductOrderEntity>("product_order") {
    val productName = varchar("product_name").primaryKey().bindTo { it.productName }
    val orderId = int("order_id").primaryKey().bindTo { it.orderId }
    val vatRate = float("vat_rate").bindTo { it.vatRate }
    val quantity = int("quantity").bindTo { it.quantity }
    val unitPriceDf = float("unit_price_df").bindTo { it.unitPriceDf }
}

interface DBProductOrderEntity: Entity<DBProductOrderEntity> {
    val productName: String
    val orderId: Int
    val vatRate: Float
    val quantity: Int
    val unitPriceDf: Float
}