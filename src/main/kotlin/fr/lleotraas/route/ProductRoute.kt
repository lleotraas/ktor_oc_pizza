package fr.lleotraas.route

import fr.lleotraas.entities.ProductToDraft
import fr.lleotraas.repository.ProductRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = ProductRepository()

fun Route.getAllProduct() {
    get("/products") {
        call.respond(HttpStatusCode.OK, repository.getAllProduct())
    }
}

fun Route.getProduct() {
    get("/product/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val product = repository.getProduct(name)

        if (product == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no product for the provided name:$name"
            )
        } else {
            call.respond(product)
        }
    }
}

fun Route.addProduct() {
    get("/add/product/id={name}/unit_price_df={udf}/description={d}/vat_rate={vat}") {
        val name = call.parameters["name"]
        val unitPriceDf = call.parameters["udf"]?.toFloat()
        val description = call.parameters["d"]
        val vatRate = call.parameters["vat"]?.toFloat()

        call.respond(HttpStatusCode.OK, repository.addProduct(ProductToDraft(
            name!!,
            unitPriceDf!!,
            description!!,
            vatRate!!
        )))
    }
}

fun Route.updateProduct() {
    get("/update/product/id={name}/new_name={n_name}/unit_price_df={udf}/description={d}/vat_rate={vat}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]
        val unitPriceDf = call.parameters["udf"]?.toFloat()
        val description = call.parameters["d"]
        val vatRate = call.parameters["vat"]?.toFloat()

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateProduct(name,
            ProductToDraft(
                newName!!,
                unitPriceDf!!,
                description!!,
                vatRate!!
            ))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no product with the name:$name")
        }
    }
}

fun Route.removeProduct() {
    get("/remove/product/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeProduct(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no product with the name:$name")
        }
    }
}