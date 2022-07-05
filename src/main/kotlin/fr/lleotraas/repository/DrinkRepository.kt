package fr.lleotraas.repository

import fr.lleotraas.database.DrinkDatabaseManager
import fr.lleotraas.entities.DrinkToDraft
import fr.lleotraas.model.Drink

class DrinkRepository {

    private val database = DrinkDatabaseManager()

    fun getAllDrink(): List<Drink> {
        return database.getAllDrink().map { Drink(it.name) }
    }

    fun getDrink(name: String): Drink? {
        return database.getDrink(name)?.let { Drink(it.name) }
    }

    fun addDrink(draft: DrinkToDraft): Drink {
        return database.addDrink(draft)
    }

    fun removeDrink(name: String): Boolean {
        return database.removeDrink(name)
    }

    fun updateDrink(name: String, draft: DrinkToDraft): Boolean {
        return database.updateDrink(name, draft)
    }
}