package fr.lleotraas.route

import fr.lleotraas.entities.ProductStockToDraft
import fr.lleotraas.repository.ProductStockRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = ProductStockRepository()

fun Route.getAllProductStock() {
    get("/product_stock") {
        call.respond(HttpStatusCode.OK, repository.getAllProductStock())
    }
}

fun Route.getProductStock() {
    get("/product_stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val productStock = repository.getProductStock(name)

        if (productStock == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no product_stock for the provided name:$name"
            )
        } else {
            call.respond(productStock)
        }
    }
}

fun Route.addProductStock() {
    get("/add/product_stock/id={name}/stock_name={sn}") {
        val name = call.parameters["name"]
        val stockName = call.parameters["sn"]
        call.respond(HttpStatusCode.OK, repository.addProductStock(ProductStockToDraft(
            name!!,
            stockName!!
        )))
    }
}

fun Route.updateProductStock() {
    get("/update/product_stock/id={name}/new_name={n_name}/stock_name={sn}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]
        val stockName = call.parameters["sn"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateProductStock(name, ProductStockToDraft(
            newName!!,
            stockName!!
        ))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no product_stock with the name:$name")
        }
    }
}

fun Route.removeProductStock() {
    get("/remove/product_stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeProductStock(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no product_stock with the name:$name")
        }
    }
}