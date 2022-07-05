package fr.lleotraas.repository

import fr.lleotraas.database.ProductOrderDatabaseManager
import fr.lleotraas.entities.ProductOrderToDraft
import fr.lleotraas.model.ProductOrder

class ProductOrderRepository {

    private val database = ProductOrderDatabaseManager()

    fun getAllProductOrder(): List<ProductOrder> {
        return database.getAllProductOrder().map { ProductOrder(
            it.productName,
            it.orderId,
            it.vatRate,
            it.quantity,
            it.unitPriceDf
        ) }
    }

    fun getProductOrder(name: String): ProductOrder? {
        return database.getProductOrder(name)?.let { ProductOrder(
            it.productName,
            it.orderId,
            it.vatRate,
            it.quantity,
            it.unitPriceDf
        ) }
    }

    fun addProductOrder(draft: ProductOrderToDraft): ProductOrder {
        return database.addProductOrder(draft)
    }

    fun removeProductOrder(name: String): Boolean {
        return database.removeProductOrder(name)
    }

    fun updateProductOrder(name: String, draft: ProductOrderToDraft): Boolean {
        return database.updateProductOrder(name, draft)
    }
}