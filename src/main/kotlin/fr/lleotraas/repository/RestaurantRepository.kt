package fr.lleotraas.repository

import fr.lleotraas.database.RestaurantDatabaseManager
import fr.lleotraas.entities.RestaurantToDraft
import fr.lleotraas.model.Restaurant

class RestaurantRepository {

    private val database = RestaurantDatabaseManager()

    fun getAllRestaurant(): List<Restaurant> {
        return database.getAllRestaurant().map { Restaurant(it.name, it.address) }
    }

    fun getRestaurant(name: String): Restaurant? {
        return database.getRestaurant(name)?.let { Restaurant(it.name, it.address) }
    }

    fun addRestaurant(restaurantToDraft: RestaurantToDraft): Restaurant {
        return database.addRestaurant(restaurantToDraft)
    }

    fun updateRestaurant(name: String, restaurantToDraft: RestaurantToDraft): Boolean {
        return database.updateRestaurant(name, restaurantToDraft)
    }

    fun removeRestaurant(name: String): Boolean {
        return database.removeRestaurant(name)
    }
}