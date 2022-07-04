package fr.lleotraas.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

object DBDessertTable: Table<DBDessertEntity>("dessert") {
    val name = varchar("name").primaryKey().bindTo { it.name }
}

interface DBDessertEntity: Entity<DBDessertEntity> {
    val name: String
}