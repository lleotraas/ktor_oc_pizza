package fr.lleotraas.route

import fr.lleotraas.entities.DrinkStockToDraft
import fr.lleotraas.repository.DrinkStockRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = DrinkStockRepository()

fun Route.getAllDrinkStock() {
    get("/drink_stock") {
        call.respond(HttpStatusCode.OK, repository.getAllDrinkStock())
    }
}

fun Route.getDrinkStock() {
    get("/drink_stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val drinkStock = repository.getDrinkStock(name)

        if (drinkStock == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no drink_stock for the provided name:$name"
            )
        } else {
            call.respond(drinkStock)
        }
    }
}

fun Route.addDrinkStock() {
    get("/add/drink_stock/id={name}") {
        val name = call.parameters["name"]
        call.respond(HttpStatusCode.OK, repository.addDrinkStock(DrinkStockToDraft(name!!)))
    }
}

fun Route.updateDrinkStock() {
    get("/update/drink_stock/id={name}/new_name={n_name}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateDrinkStock(name, DrinkStockToDraft(newName!!))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no drink_stock with the name:$name")
        }
    }
}

fun Route.removeDrinkStock() {
    get("/remove/drink_stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeDrinkStock(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no drink_stock with the name:$name")
        }
    }
}