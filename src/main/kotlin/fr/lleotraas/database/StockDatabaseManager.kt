package fr.lleotraas.database

import fr.lleotraas.database.Utils.databaseName
import fr.lleotraas.database.Utils.hostname
import fr.lleotraas.database.Utils.password
import fr.lleotraas.database.Utils.username
import fr.lleotraas.entities.DBStockEntity
import fr.lleotraas.entities.DBStockTable
import fr.lleotraas.entities.StockToDraft
import fr.lleotraas.model.Stock
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class StockDatabaseManager {

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllStock(): List<DBStockEntity> {
        return ktormDatabase.sequenceOf(DBStockTable).toList()
    }

    fun getStock(name: String): DBStockEntity {
        return ktormDatabase.sequenceOf(DBStockTable).first { it.name eq name}
    }

    fun addStock(stock: StockToDraft): Stock {
        ktormDatabase.insert(DBStockTable) {
            set(it.name, stock.productName)
            set(it.remainingQuantity, stock.remainingQuantity)
        }
        return Stock(stock.productName, stock.remainingQuantity)
    }

    fun updateStock(name: String, stock: StockToDraft): Boolean {
        val updatedRow = ktormDatabase.update(DBStockTable) {
            set(it.name, stock.productName)
            set(it.remainingQuantity, stock.remainingQuantity)
            where { it.name eq name }
        }
        return updatedRow > 0
    }

    fun removeStock(name: String): Boolean {
        val removedRow = ktormDatabase.delete(DBStockTable) {
            it.name eq name
        }
        return removedRow > 0
    }
}