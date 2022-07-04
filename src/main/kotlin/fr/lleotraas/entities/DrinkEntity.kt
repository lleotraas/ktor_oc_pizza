package fr.lleotraas.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

object DBDrinkTable: Table<DBDrinkEntity>("drink") {
    val name = varchar("name").primaryKey().bindTo { it.name }
}

interface DBDrinkEntity: Entity<DBDrinkEntity> {
    val name: String
}