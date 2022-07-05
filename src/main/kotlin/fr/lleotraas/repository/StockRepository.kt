package fr.lleotraas.repository

import fr.lleotraas.database.StockDatabaseManager
import fr.lleotraas.entities.StockToDraft
import fr.lleotraas.model.Stock

class StockRepository {

    private val database = StockDatabaseManager()

    fun getAllStock(): List<Stock> {
        return database.getAllStock().map { Stock(
            it.name,
            it.remainingQuantity
        ) }
    }

    fun getStock(name: String): Stock? {
        return database.getStock(name)?.let { Stock(
            it.name,
            it.remainingQuantity
        ) }
    }

    fun addStock(draft: StockToDraft): Stock {
        return database.addStock(draft)
    }

    fun removeStock(name: String): Boolean {
        return database.removeStock(name)
    }

    fun updateStock(name: String, draft: StockToDraft): Boolean {
        return database.updateStock(name, draft)
    }
}