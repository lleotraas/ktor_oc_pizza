package fr.lleotraas.database

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

class DrinkStockDatabaseMananger {

    private val hostname = "localhost"
    private val databaseName = "oc_pizza"
    private val username = "root"
    private val password = "!Ceberg1"

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllDrink(): List<DBDrinkStockEntity> {
        return ktormDatabase.sequenceOf(DBDrinkStockTable).toList()
    }

    fun getDrink(name: String): DBDrinkStockEntity {
        return ktormDatabase.sequenceOf(DBDrinkStockTable).first { it.name eq name }
    }

    fun addDrink(drinkStockToDraft: DrinkStockToDraft): Drink {
        ktormDatabase.insert(DBDrinkTable) {
            set(it.name, drinkStockToDraft.name)
        }
        return Drink(drinkStockToDraft.name)
    }

    fun updateDrink(name: String, drinkStockToDraft: DrinkStockToDraft): Boolean {
        val updateRows = ktormDatabase.update(DBDrinkStockTable) {
            set(it.name, drinkStockToDraft.name)
            where { it.name eq name }
        }
        return updateRows > 0
    }

    fun removeDrink(name: String): Boolean {
        val deletedRow = ktormDatabase.delete(DBDrinkStockTable) {
            it.name eq name
        }
        return deletedRow > 0
    }
}