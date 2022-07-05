package fr.lleotraas.repository

import fr.lleotraas.database.DrinkStockDatabaseManager
import fr.lleotraas.entities.DrinkStockToDraft
import fr.lleotraas.model.DrinkStock

class DrinkStockRepository {

    private val database = DrinkStockDatabaseManager()

    fun getAllDrinkStock(): List<DrinkStock> {
        return database.getAllDrink().map { DrinkStock(it.name) }
    }

    fun getDrinkStock(name: String): DrinkStock? {
        return database.getDrink(name)?.let { DrinkStock(it.name) }
    }

    fun addDrinkStock(draft: DrinkStockToDraft): DrinkStock {
        return database.addDrink(draft)
    }

    fun removeDrinkStock(name: String): Boolean {
        return database.removeDrink(name)
    }

    fun updateDrinkStock(name: String, draft: DrinkStockToDraft): Boolean {
        return database.updateDrink(name, draft)
    }
}