package fr.lleotraas.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.varchar

object DBPizzaTable: Table<DBPizzaEntity>("pizza") {
    val name = varchar("name").primaryKey().bindTo { it.name }
    val recipe = varchar("recipe").bindTo { it.recipe }
}

interface DBPizzaEntity: Entity<DBPizzaEntity> {
    val name: String
    val recipe: String
}