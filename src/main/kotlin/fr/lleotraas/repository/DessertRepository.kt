package fr.lleotraas.repository

import fr.lleotraas.database.DessertDatabaseManager
import fr.lleotraas.entities.DessertToDraft
import fr.lleotraas.model.Dessert

class DessertRepository {

    private val database = DessertDatabaseManager()

    fun getAllDessert(): List<Dessert> {
        return database.getAllDessert().map { Dessert(it.name) }
    }

    fun getDessert(name: String): Dessert? {
        return database.getDessert(name)?.let { Dessert(it.name) }
    }

    fun addDessert(draft: DessertToDraft): Dessert {
        return database.addDessert(draft)
    }

    fun removeDessert(name: String): Boolean {
        return database.removeDessert(name)
    }

    fun updateDessert(name: String, draft: DessertToDraft): Boolean {
        return database.updateDessert(name, draft)
    }
}