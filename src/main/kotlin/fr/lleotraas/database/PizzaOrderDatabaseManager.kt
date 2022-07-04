package fr.lleotraas.database

import fr.lleotraas.entities.DBPizzaOrderEntity
import fr.lleotraas.entities.DBPizzaOrderTable
import fr.lleotraas.entities.PizzaOrderToDraft
import fr.lleotraas.model.PizzaOrder

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.first

import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class PizzaOrderDatabaseManager {

    private val hostname = "localhost"
    private val databaseName = "oc_pizza"
    private val username = "root"
    private val password = "!Ceberg1"

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllPizzaOrder(): List<DBPizzaOrderEntity> {
        return ktormDatabase.sequenceOf(DBPizzaOrderTable).toList()
    }

    fun getPizzaOrder(id: Int): DBPizzaOrderEntity {
        return ktormDatabase.sequenceOf(DBPizzaOrderTable).first { it.id eq id }
    }

    fun addPizzaOrder(pizzaOrderToDraft: PizzaOrderToDraft): PizzaOrder {
        val insertedId = ktormDatabase.insertAndGenerateKey(DBPizzaOrderTable) {
            set(it.userId, pizzaOrderToDraft.userId)
            set(it.restaurantName, pizzaOrderToDraft.restaurantName)
            set(it.creationDate, pizzaOrderToDraft.creationDate)
            set(it.deliveryHour, pizzaOrderToDraft.deliveryHour)
            set(it.deliveryState, pizzaOrderToDraft.deliveryState)
            set(it.isPaid, pizzaOrderToDraft.isPaid)
            set(it.isDelivery, pizzaOrderToDraft.isDelivery)
            set(it.amount, pizzaOrderToDraft.amount)
        } as Int
        return PizzaOrder(
            insertedId,
            pizzaOrderToDraft.userId,
            pizzaOrderToDraft.restaurantName,
            pizzaOrderToDraft.creationDate,
            pizzaOrderToDraft.deliveryHour,
            pizzaOrderToDraft.deliveryState,
            pizzaOrderToDraft.isPaid,
            pizzaOrderToDraft.isDelivery,
            pizzaOrderToDraft.amount
        )
    }

    fun updatePizzaOrder(id: Int, pizzaOrderToDraft: PizzaOrderToDraft): Boolean {
        val updateRows = ktormDatabase.update(DBPizzaOrderTable) {
            set(it.userId, pizzaOrderToDraft.userId)
            set(it.restaurantName, pizzaOrderToDraft.restaurantName)
            set(it.creationDate, pizzaOrderToDraft.creationDate)
            set(it.deliveryHour, pizzaOrderToDraft.deliveryHour)
            set(it.deliveryState, pizzaOrderToDraft.deliveryState)
            set(it.isPaid, pizzaOrderToDraft.isPaid)
            set(it.isDelivery, pizzaOrderToDraft.isDelivery)
            set(it.amount, pizzaOrderToDraft.amount)
            where { it.id eq id }
        }
        return updateRows > 0
    }

    fun removePizzaOrder(id: Int): Boolean {
        val deletedRow = ktormDatabase.delete(DBPizzaOrderTable) {
            it.id eq id
        }
        return deletedRow > 0
    }
}