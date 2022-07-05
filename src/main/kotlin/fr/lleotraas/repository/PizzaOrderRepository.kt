package fr.lleotraas.repository

import fr.lleotraas.database.DessertDatabaseManager
import fr.lleotraas.database.PizzaOrderDatabaseManager
import fr.lleotraas.entities.DessertToDraft
import fr.lleotraas.entities.PizzaOrderToDraft
import fr.lleotraas.model.Dessert
import fr.lleotraas.model.PizzaOrder

class PizzaOrderRepository {

    private val database = PizzaOrderDatabaseManager()

    fun getAllPizzaOrder(): List<PizzaOrder> {
        return database.getAllPizzaOrder().map { PizzaOrder(
            it.id,
            it.userId,
            it.restaurantName,
            it.creationDate,
            it.deliveryHour,
            it.deliveryState,
            it.isPaid,
            it.isDelivery,
            it.amount
        ) }
    }

    fun getPizzaOrder(id: Int): PizzaOrder? {
        return database.getPizzaOrder(id)?.let { PizzaOrder(
            it.id,
            it.userId,
            it.restaurantName,
            it.creationDate,
            it.deliveryHour,
            it.deliveryState,
            it.isPaid,
            it.isDelivery,
            it.amount
        ) }
    }

    fun addPizzaOrder(draft: PizzaOrderToDraft): PizzaOrder {
        return database.addPizzaOrder(draft)
    }

    fun removePizzaOrder(id: Int): Boolean {
        return database.removePizzaOrder(id)
    }

    fun updatePizzaOrder(id: Int, draft: PizzaOrderToDraft): Boolean {
        return database.updatePizzaOrder(id, draft)
    }
}