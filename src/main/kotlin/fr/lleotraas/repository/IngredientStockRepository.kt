package fr.lleotraas.repository

import fr.lleotraas.database.IngredientDatabaseManager
import fr.lleotraas.entities.IngredientStockToDraft
import fr.lleotraas.model.IngredientStock

class IngredientStockRepository {

    private val database = IngredientDatabaseManager()

    fun getAllIngredientStock(): List<IngredientStock> {
        return database.getAllIngredientStock().map { IngredientStock(it.name) }
    }

    fun getIngredientStock(name: String): IngredientStock? {
        return database.getIngredientStock(name)?.let { IngredientStock(it.name) }
    }

    fun addIngredientStock(draft: IngredientStockToDraft): IngredientStock {
        return database.addIngredientStock(draft)
    }

    fun removeIngredientStock(name: String): Boolean {
        return database.removeIngredientStock(name)
    }

    fun updateIngredientStock(name: String, draft: IngredientStockToDraft): Boolean {
        return database.updateIngredientStock(name, draft)
    }
}