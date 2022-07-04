package fr.lleotraas.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

object DBRestaurantTable: Table<DBRestaurantEntity>("restaurant") {
    val name = varchar("name").primaryKey().bindTo { it.name }
    val address = varchar("address").bindTo { it.address }
}

interface DBRestaurantEntity: Entity<DBRestaurantEntity> {
    val name: String
    val address: String
}