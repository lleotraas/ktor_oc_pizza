package fr.lleotraas.route

import fr.lleotraas.entities.DessertStockToDraft
import fr.lleotraas.repository.DessertStockRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = DessertStockRepository()

fun Route.getAllDessertStock() {
    get("/desserts_stock") {
        call.respond(HttpStatusCode.OK, repository.getAllDessertStock())
    }
}

fun Route.getDessertStock() {
    get("/dessert_stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val dessertStock = repository.getDessertStock(name)

        if (dessertStock == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no dessert_stock for the provided name:$name"
            )
        } else {
            call.respond(dessertStock)
        }
    }
}

fun Route.addDessertStock() {
    get("/add/dessert_stock/id={name}") {
        val name = call.parameters["name"]
        call.respond(HttpStatusCode.OK, repository.addDessertStock(DessertStockToDraft(name!!)))
    }
}

fun Route.updateDessertStock() {
    get("/update/dessert_stock/id={name}/new_name={n_name}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateDessertStock(name, DessertStockToDraft(newName!!))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no dessert_stock with the name:$name")
        }
    }
}

fun Route.removeDessertStock() {
    get("/remove/dessert_stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeDessertStock(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no dessert_stock with the name:$name")
        }
    }
}