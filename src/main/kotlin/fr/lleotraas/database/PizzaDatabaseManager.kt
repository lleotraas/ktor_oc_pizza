package fr.lleotraas.database

import fr.lleotraas.database.Utils.databaseName
import fr.lleotraas.database.Utils.hostname
import fr.lleotraas.database.Utils.password
import fr.lleotraas.database.Utils.username
import fr.lleotraas.entities.*
import fr.lleotraas.model.Pizza
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class PizzaDatabaseManager {

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllPizza(): List<DBPizzaEntity> {
        return ktormDatabase.sequenceOf(DBPizzaTable).toList()
    }

    fun getPizza(name: String): DBPizzaEntity {
        return ktormDatabase.sequenceOf(DBPizzaTable).first { it.name eq name }
    }

    fun addPizza(pizzaToDraft: PizzaToDraft): Pizza {
        ktormDatabase.insert(DBPizzaTable) {
            set(it.name, pizzaToDraft.name)
            set(it.recipe, pizzaToDraft.recipe)
        }
        return Pizza(pizzaToDraft.name, pizzaToDraft.recipe)
    }

    fun updatePizza(name: String, pizzaToDraft: PizzaToDraft): Boolean {
        val updateRows = ktormDatabase.update(DBPizzaTable) {
            set(it.name, pizzaToDraft.name)
            set(it.recipe, pizzaToDraft.recipe)
            where { it.name eq name }
        }
        return updateRows > 0
    }

    fun removePizza(name: String): Boolean {
        val deletedRow = ktormDatabase.delete(DBPizzaTable) {
            it.name eq name
        }
        return deletedRow > 0
    }
}