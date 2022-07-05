package fr.lleotraas.database

import fr.lleotraas.database.Utils.databaseName
import fr.lleotraas.database.Utils.hostname
import fr.lleotraas.database.Utils.password
import fr.lleotraas.database.Utils.username
import fr.lleotraas.entities.*
import fr.lleotraas.model.Drink
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DrinkDatabaseManager {

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllDrink(): List<DBDrinkEntity> {
        return ktormDatabase.sequenceOf(DBDrinkTable).toList()
    }

    fun getDrink(name: String): DBDrinkEntity {
        return ktormDatabase.sequenceOf(DBDrinkTable).first { it.name eq name }
    }

    fun addDrink(drinkToDraft: DrinkToDraft): Drink {
        ktormDatabase.insert(DBDrinkTable) {
            set(it.name, drinkToDraft.name)
        }
        return Drink(drinkToDraft.name)
    }

    fun updateDrink(name: String, drinkToDraft: DrinkToDraft): Boolean {
        val updateRows = ktormDatabase.update(DBDrinkTable) {
            set(it.name, drinkToDraft.name)
            where { it.name eq name }
        }
        return updateRows > 0
    }

    fun removeDrink(name: String): Boolean {
        val deletedRow = ktormDatabase.delete(DBDrinkTable) {
            it.name eq name
        }
        return deletedRow > 0
    }
}