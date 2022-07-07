package fr.lleotraas.route

import fr.lleotraas.entities.IngredientStockToDraft
import fr.lleotraas.repository.IngredientStockRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = IngredientStockRepository()

fun Route.getAllIngredientStock() {
    get("/ingredient_stock") {
        call.respond(HttpStatusCode.OK, repository.getAllIngredientStock())
    }
}

fun Route.getIngredientStock() {
    get("/ingredient_stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val ingredientStock = repository.getIngredientStock(name)

        if (ingredientStock == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no ingredient_stock for the provided name:$name"
            )
        } else {
            call.respond(ingredientStock)
        }
    }
}

fun Route.addIngredientStock() {
    get("/add/ingredient_stock/id={name}") {
        val name = call.parameters["name"]
        call.respond(HttpStatusCode.OK, repository.addIngredientStock(IngredientStockToDraft(name!!)))
    }
}

fun Route.updateIngredientStock() {
    get("/update/ingredient_stock/id={name}/new_name={n_name}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateIngredientStock(name, IngredientStockToDraft(newName!!))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no ingredient_stock with the name:$name")
        }
    }
}

fun Route.removeIngredientStock() {
    get("/remove/ingredient_stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeIngredientStock(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no ingredient_stock with the name:$name")
        }
    }
}