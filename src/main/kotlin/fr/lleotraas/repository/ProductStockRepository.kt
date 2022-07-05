package fr.lleotraas.repository

import fr.lleotraas.database.ProductStockDatabaseManager
import fr.lleotraas.entities.ProductStockToDraft
import fr.lleotraas.model.ProductStock


class ProductStockRepository {

    private val database = ProductStockDatabaseManager()

    fun getAllProductStock(): List<ProductStock> {
        return database.getAllProductStock().map { ProductStock(
            it.productName,
            it.stockName
        ) }
    }

    fun getProductStock(name: String): ProductStock? {
        return database.getProductStock(name)?.let { ProductStock(
            it.productName,
            it.stockName
        ) }
    }

    fun addProductStock(draft: ProductStockToDraft): ProductStock {
        return database.addProductStock(draft)
    }

    fun removeProductStock(name: String): Boolean {
        return database.removeProductStock(name)
    }

    fun updateProductStock(name: String, draft: ProductStockToDraft): Boolean {
        return database.updateProductStock(name, draft)
    }
}