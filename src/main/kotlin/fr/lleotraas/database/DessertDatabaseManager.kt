package fr.lleotraas.database

import fr.lleotraas.database.Utils.databaseName
import fr.lleotraas.database.Utils.hostname
import fr.lleotraas.database.Utils.password
import fr.lleotraas.database.Utils.username
import fr.lleotraas.entities.DBDessertEntity
import fr.lleotraas.entities.DBDessertTable
import fr.lleotraas.entities.DessertToDraft
import fr.lleotraas.model.Dessert
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DessertDatabaseManager {

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://${hostname}:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllDessert(): List<DBDessertEntity> {
        return ktormDatabase.sequenceOf(DBDessertTable).toList()
    }

    fun getDessert(name: String): DBDessertEntity {
        return ktormDatabase.sequenceOf(DBDessertTable).first { it.name eq name }
    }

    fun addDessert(dessertToDraft: DessertToDraft): Dessert {
        ktormDatabase.insert(DBDessertTable) {
            set(it.name, dessertToDraft.name)
        }
        return Dessert(dessertToDraft.name)
    }

    fun updateDessert(name: String, dessertToDraft: DessertToDraft): Boolean {
        val updateRows = ktormDatabase.update(DBDessertTable) {
            set(it.name, dessertToDraft.name)
            where { it.name eq name }
        }
        return updateRows > 0
    }

    fun removeDessert(name: String): Boolean {
        val deletedRow = ktormDatabase.delete(DBDessertTable) {
            it.name eq name
        }
        return deletedRow > 0
    }
}