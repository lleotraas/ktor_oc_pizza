package fr.lleotraas.entities

import fr.lleotraas.entities.DBDrinkStockTable.bindTo
import fr.lleotraas.entities.DBDrinkStockTable.primaryKey
import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

object DBIngredientStockTable: Table<DBIngredientStockEntity>("ingredient_stock") {
    val name = varchar("name").primaryKey().bindTo { it.name }
}

interface DBIngredientStockEntity: Entity<DBIngredientStockEntity> {
    val name: String
}