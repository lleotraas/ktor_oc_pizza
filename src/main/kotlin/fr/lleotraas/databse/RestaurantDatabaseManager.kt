package fr.lleotraas.databse

import fr.lleotraas.entities.DBRestaurantEntity
import fr.lleotraas.entities.DBRestaurantTable
import fr.lleotraas.entities.RestaurantToDraft
import fr.lleotraas.model.Restaurant
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class RestaurantDatabaseManager {

    private val hostname = "localhost"
    private val databaseName = "oc_pizza"
    private val username = "root"
    private val password = "!Ceberg1"

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllRestaurant(): List<DBRestaurantEntity> {
        return ktormDatabase.sequenceOf(DBRestaurantTable).toList()
    }

    fun getRestaurant(name: String): DBRestaurantEntity {
        return ktormDatabase.sequenceOf(DBRestaurantTable).first { it.name eq name }
    }

    fun addRestaurant(restaurantToDraft: RestaurantToDraft): Restaurant {
        ktormDatabase.insert(DBRestaurantTable) {
            set(it.name, restaurantToDraft.name)
            set(it.address, restaurantToDraft.address)
        }
        return Restaurant(restaurantToDraft.name, restaurantToDraft.address)
    }

    fun updateRestaurant(name: String, restaurantToDraft: RestaurantToDraft): Boolean {
        val updateRows = ktormDatabase.update(DBRestaurantTable) {
            set(it.name, restaurantToDraft.name)
            set(it.address, restaurantToDraft.address)
            where { it.name eq name }
        }
        return updateRows > 0
    }

    fun removeRestaurant(name: String): Boolean {
        val deletedRow = ktormDatabase.delete(DBRestaurantTable) {
            it.name eq name
        }
        return deletedRow > 0
    }
}