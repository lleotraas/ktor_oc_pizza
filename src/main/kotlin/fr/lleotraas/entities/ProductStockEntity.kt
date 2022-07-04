package fr.lleotraas.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

object DBProductStockTable: Table<DBProductStockEntity>("product_stock") {
    val productName = varchar("product_name").primaryKey().bindTo { it.productName }
    val stockName = varchar("stock_name").primaryKey().bindTo { it.stockName }
}

interface DBProductStockEntity: Entity<DBProductStockEntity> {
    val productName: String
    val stockName: String
}