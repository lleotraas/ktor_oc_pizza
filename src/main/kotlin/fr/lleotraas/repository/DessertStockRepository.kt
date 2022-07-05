package fr.lleotraas.repository

import fr.lleotraas.database.DessertStockDatabaseManager
import fr.lleotraas.entities.DessertStockToDraft
import fr.lleotraas.model.DessertStock

class DessertStockRepository {

    private val database = DessertStockDatabaseManager()

    fun getAllDessertStock(): List<DessertStock> {
        return database.getAllDessertStock().map { DessertStock(it.name) }
    }

    fun getDessertStock(name: String): DessertStock? {
        return database.getDessertStock(name)?.let { DessertStock(it.name) }
    }

    fun addDessertStock(draft: DessertStockToDraft): DessertStock {
        return database.addDessertStock(draft)
    }

    fun removeDessertStock(name: String): Boolean {
        return database.removeDessertStock(name)
    }

    fun updateDessertStock(name: String, draft: DessertStockToDraft): Boolean {
        return database.updateDessertStock(name, draft)
    }
}