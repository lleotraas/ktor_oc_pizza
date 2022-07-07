package fr.lleotraas.route

import fr.lleotraas.entities.ProductOrderToDraft
import fr.lleotraas.repository.ProductOrderRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = ProductOrderRepository()

fun Route.getAllProductOrder() {
    get("/product_order") {
        call.respond(HttpStatusCode.OK, repository.getAllProductOrder())
    }
}

fun Route.getProductOrder() {
    get("/product_order/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val productOrder = repository.getProductOrder(name)

        if (productOrder == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no product_order for the provided name:$name"
            )
        } else {
            call.respond(productOrder)
        }
    }
}

fun Route.addProductOrder() {
    get("/add/product_order/id={name}/order_id={oid}/vat_rate={vat}/quantity={q}/unit_price_df={upd}") {
        val name = call.parameters["name"]
        val orderId = call.parameters["oid"]?.toIntOrNull()
        val vatRate = call.parameters["vat"]?.toFloat()
        val quantity = call.parameters["q"]?.toIntOrNull()
        val unitPriceDf = call.parameters["upd"]?.toFloat()
            call.respond(HttpStatusCode.OK, repository.addProductOrder(ProductOrderToDraft(
                name!!,
                orderId!!,
                vatRate!!,
                quantity!!,
                unitPriceDf!!
            )))
    }
}

fun Route.updateProductOrder() {
    get("/update/product_order/id={name}/new_name={n_name}/order_id={oid}/vat_rate={vat}/quantity={q}/unit_price_df={upd}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]
        val orderId = call.parameters["oid"]?.toIntOrNull()
        val vatRate = call.parameters["vat"]?.toFloat()
        val quantity = call.parameters["q"]?.toIntOrNull()
        val unitPriceDf = call.parameters["upd"]?.toFloat()

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateProductOrder(name,
            ProductOrderToDraft(
                newName!!,
                orderId!!,
                vatRate!!,
                quantity!!,
                unitPriceDf!!
        ))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no product_order with the name:$name")
        }
    }
}

fun Route.removeProductOrder() {
    get("/remove/product_order/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeProductOrder(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no product_order with the name:$name")
        }
    }
}