package fr.lleotraas.repository

import fr.lleotraas.database.DessertDatabaseManager
import fr.lleotraas.database.PizzaDatabaseManager
import fr.lleotraas.entities.DessertToDraft
import fr.lleotraas.entities.PizzaToDraft
import fr.lleotraas.model.Dessert
import fr.lleotraas.model.Pizza

class PizzaRepository {

    private val database = PizzaDatabaseManager()

    fun getAllPizza(): List<Pizza> {
        return database.getAllPizza().map { Pizza(
            it.name,
            it.recipe
        ) }
    }

    fun getPizza(name: String): Pizza? {
        return database.getPizza(name)?.let { Pizza(
            it.name,
            it.recipe
        ) }
    }

    fun addPizza(draft: PizzaToDraft): Pizza {
        return database.addPizza(draft)
    }

    fun removePizza(name: String): Boolean {
        return database.removePizza(name)
    }

    fun updatePizza(name: String, draft: PizzaToDraft): Boolean {
        return database.updatePizza(name, draft)
    }
}