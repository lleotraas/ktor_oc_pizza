package fr.lleotraas.entities

import fr.lleotraas.entities.DBDessertTable.bindTo
import fr.lleotraas.entities.DBDessertTable.primaryKey
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

object DBDessertStockTable: Table<DBDessertStockEntity>("dessert_stock") {
    val name = varchar("name").primaryKey().bindTo { it.name }
}

interface DBDessertStockEntity: Entity<DBDessertStockEntity> {
    val name: String
}