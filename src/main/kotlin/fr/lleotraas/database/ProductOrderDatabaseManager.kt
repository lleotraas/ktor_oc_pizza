package fr.lleotraas.database

import fr.lleotraas.database.Utils.databaseName
import fr.lleotraas.database.Utils.hostname
import fr.lleotraas.database.Utils.password
import fr.lleotraas.database.Utils.username
import fr.lleotraas.entities.*
import fr.lleotraas.model.ProductOrder
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class ProductOrderDatabaseManager {


    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllProductOrder(): List<DBProductOrderEntity> {
        return ktormDatabase.sequenceOf(DBProductOrderTable).toList()
    }

    fun getProductOrder(productName: String): DBProductOrderEntity {
        return ktormDatabase.sequenceOf(DBProductOrderTable).first { it.productName eq productName}
    }

    fun addProductOrder(productOrderToDraft: ProductOrderToDraft): ProductOrder {
        ktormDatabase.insert(DBProductOrderTable) {
            set(it.productName, productOrderToDraft.productName)
            set(it.orderId, productOrderToDraft.orderId)
            set(it.vatRate, productOrderToDraft.vatRate)
            set(it.quantity, productOrderToDraft.quantity)
            set(it.unitPriceDf, productOrderToDraft.unitPriceDf)
        }
        return ProductOrder(
            productOrderToDraft.productName,
            productOrderToDraft.orderId,
            productOrderToDraft.vatRate,
            productOrderToDraft.quantity,
            productOrderToDraft.unitPriceDf
        )
    }

    fun updateProductOrder(productName: String, productOrderToDraft: ProductOrderToDraft): Boolean {
        val updatedRow = ktormDatabase.update(DBProductOrderTable) {
            set(it.productName, productOrderToDraft.productName)
            set(it.orderId, productOrderToDraft.orderId)
            set(it.vatRate, productOrderToDraft.vatRate)
            set(it.quantity, productOrderToDraft.quantity)
            set(it.unitPriceDf, productOrderToDraft.unitPriceDf)
            where { it.productName eq productName }
        }
        return updatedRow > 0
    }

    fun removeProductOrder(productName: String): Boolean {
        val removedRow = ktormDatabase.delete(DBProductOrderTable) {
            it.productName eq productName
        }
        return removedRow > 0
    }

}