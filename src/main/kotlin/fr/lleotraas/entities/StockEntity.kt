package fr.lleotraas.entities

import fr.lleotraas.entities.DBProductStockTable.bindTo
import fr.lleotraas.entities.DBProductStockTable.primaryKey
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBStockTable: Table<DBStockEntity>("stock") {
    val name = varchar("name").primaryKey().bindTo { it.name }
    val remainingQuantity = int("remaining_quantity").bindTo { it.remainingQuantity }
}

interface DBStockEntity: Entity<DBStockEntity> {
    val name: String
    val remainingQuantity: Int
}