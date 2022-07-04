package fr.lleotraas.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.float
import org.ktorm.schema.varchar

object DBProductTable: Table<DBProductEntity>("product") {
    val name = varchar("name").primaryKey().bindTo { it.name }
    val unitPriceDf = float("unit_price_df").bindTo { it.unitPriceDf }
    val description = varchar("description").primaryKey().bindTo { it.description }
    val vatRate = float("vat_rate").bindTo { it.vatRate }
}

interface DBProductEntity: Entity<DBProductEntity> {
    val name: String
    val unitPriceDf: Float
    val description: String
    val vatRate: Float
}