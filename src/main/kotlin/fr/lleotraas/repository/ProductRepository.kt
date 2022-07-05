package fr.lleotraas.repository

import fr.lleotraas.database.ProductDatabaseManager
import fr.lleotraas.entities.ProductToDraft
import fr.lleotraas.model.Product


class ProductRepository {

    private val database = ProductDatabaseManager()

    fun getAllProduct(): List<Product> {
        return database.getAllProduct().map { Product(
            it.name,
            it.unitPriceDf,
            it.description,
            it.vatRate
        ) }
    }

    fun getProduct(name: String): Product? {
        return database.getProduct(name)?.let { Product(
            it.name,
            it.unitPriceDf,
            it.description,
            it.vatRate
        ) }
    }

    fun addProduct(draft: ProductToDraft): Product {
        return database.addProduct(draft)
    }

    fun removeProduct(name: String): Boolean {
        return database.removeProduct(name)
    }

    fun updateProduct(name: String, draft: ProductToDraft): Boolean {
        return database.updateProduct(name, draft)
    }
}