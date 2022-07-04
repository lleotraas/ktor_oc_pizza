package fr.lleotraas.entities

import fr.lleotraas.entities.DBDrinkTable.bindTo
import fr.lleotraas.entities.DBDrinkTable.primaryKey
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

object DBDrinkStockTable: Table<DBDrinkStockEntity>("drink_stock") {
    val name = varchar("name").primaryKey().bindTo { it.name }
}

interface DBDrinkStockEntity: Entity<DBDrinkStockEntity> {
    val name: String
}