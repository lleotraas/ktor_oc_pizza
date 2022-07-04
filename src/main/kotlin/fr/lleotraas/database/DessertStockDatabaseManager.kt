package fr.lleotraas.database

import fr.lleotraas.entities.*
import fr.lleotraas.model.DessertStock
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DessertStockDatabaseManager {

    private val hostname = "localhost"
    private val databaseName = "oc_pizza"
    private val username = "root"
    private val password = "!Ceberg1"

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllDessertStock(): List<DBDessertStockEntity> {
        return ktormDatabase.sequenceOf(DBDessertStockTable).toList()
    }

    fun getDessertStock(name: String): DBDessertStockEntity {
        return ktormDatabase.sequenceOf(DBDessertStockTable).first { it.name eq name }
    }

    fun addDessertStock(dessertStockToDraft: DessertStockToDraft): DessertStock {
        ktormDatabase.insert(DBDessertStockTable) {
            set(it.name, dessertStockToDraft.name)
        }
        return DessertStock(dessertStockToDraft.name)
    }

    fun updateDessertStock(name: String, dessertStockToDraft: DessertStockToDraft): Boolean {
        val updateRows = ktormDatabase.update(DBDessertStockTable) {
            set(it.name, dessertStockToDraft.name)
            where { it.name eq name }
        }
        return updateRows > 0
    }

    fun removeDessertStock(name: String): Boolean {
        val deletedRow = ktormDatabase.delete(DBDessertStockTable) {
            it.name eq name
        }
        return deletedRow > 0
    }
}