package fr.lleotraas.database

import fr.lleotraas.entities.*
import fr.lleotraas.model.IngredientStock
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class IngredientDatabaseManager {

    private val hostname = "localhost"
    private val databaseName = "oc_pizza"
    private val username = "root"
    private val password = "!Ceberg1"

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllIngredientStock(): List<DBIngredientStockEntity> {
        return ktormDatabase.sequenceOf(DBIngredientStockTable).toList()
    }

    fun getIngredientStock(name: String): DBIngredientStockEntity {
        return ktormDatabase.sequenceOf(DBIngredientStockTable).first { it.name eq name }
    }

    fun addIngredientStock(ingredientStockToDraft: IngredientStockToDraft): IngredientStock {
        ktormDatabase.insert(DBIngredientStockTable) {
            set(it.name, ingredientStockToDraft.name)
        }
        return IngredientStock(ingredientStockToDraft.name)
    }

    fun updateIngredientStock(name: String, ingredientStockToDraft: IngredientStockToDraft): Boolean {
        val updateRows = ktormDatabase.update(DBIngredientStockTable) {
            set(it.name, ingredientStockToDraft.name)
            where { it.name eq name }
        }
        return updateRows > 0
    }

    fun removeIngredientStock(name: String): Boolean {
        val deletedRow = ktormDatabase.delete(DBIngredientStockTable) {
            it.name eq name
        }
        return deletedRow > 0
    }
}