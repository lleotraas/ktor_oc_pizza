package fr.lleotraas.database

import fr.lleotraas.database.Utils.databaseName
import fr.lleotraas.database.Utils.hostname
import fr.lleotraas.database.Utils.password
import fr.lleotraas.database.Utils.username
import fr.lleotraas.entities.*
import fr.lleotraas.model.ProductStock
import org.ktorm.database.Database
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insert
import org.ktorm.dsl.update
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class ProductStockDatabaseManager {

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllProductStock(): List<DBProductStockEntity> {
        return ktormDatabase.sequenceOf(DBProductStockTable).toList()
    }

    fun getProductStock(productName: String): DBProductStockEntity {
        return ktormDatabase.sequenceOf(DBProductStockTable).first { it.productName eq productName}
    }

    fun addProductStock(productStock: ProductStockToDraft): ProductStock {
        ktormDatabase.insert(DBProductStockTable) {
            set(it.productName, productStock.productName)
            set(it.stockName, productStock.stockName)
        }
        return ProductStock(productStock.productName, productStock.stockName)
    }

    fun updateProductStock(productName: String, productStock: ProductStockToDraft): Boolean {
        val updatedRow = ktormDatabase.update(DBProductStockTable) {
            set(it.productName, productStock.productName)
            set(it.stockName, productStock.stockName)
            where { it.productName eq productName }
        }
        return updatedRow > 0
    }

    fun removeProductStock(productName: String): Boolean {
        val removedRow = ktormDatabase.delete(DBProductStockTable) {
            it.productName eq productName
        }
        return removedRow > 0
    }

}