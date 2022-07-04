package fr.lleotraas.database

import fr.lleotraas.entities.*
import fr.lleotraas.model.Product
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class ProductDatabaseManager {

    private val hostname = "localhost"
    private val databaseName = "oc_pizza"
    private val username = "root"
    private val password = "!Ceberg1"

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllProduct(): List<DBProductEntity> {
        return ktormDatabase.sequenceOf(DBProductTable).toList()
    }

    fun getProduct(name: String): DBProductEntity {
        return ktormDatabase.sequenceOf(DBProductTable).first { it.name eq name}
    }

    fun addProduct(productToDraft: ProductToDraft): Product {
        ktormDatabase.insert(DBProductTable) {
            set(it.name, productToDraft.name)
            set(it.unitPriceDf, productToDraft.unitPriceDf)
            set(it.description, productToDraft.description)
            set(it.vatRate, productToDraft.vatRate)
        }
        return Product(
            productToDraft.name,
            productToDraft.vatRate,
            productToDraft.description,
            productToDraft.unitPriceDf
        )
    }

    fun updateProduct(name: String, productToDraft: ProductToDraft): Boolean {
        val updatedRow = ktormDatabase.update(DBProductTable) {
            set(it.name, productToDraft.name)
            set(it.unitPriceDf, productToDraft.unitPriceDf)
            set(it.description, productToDraft.description)
            set(it.vatRate, productToDraft.vatRate)
            where { it.name eq name }
        }
        return updatedRow > 0
    }

    fun removeProduct(name: String): Boolean {
        val removedRow = ktormDatabase.delete(DBProductTable) {
            it.name eq name
        }
        return removedRow > 0
    }
}