package fr.lleotraas.route

import fr.lleotraas.entities.DessertToDraft
import fr.lleotraas.entities.DrinkToDraft
import fr.lleotraas.repository.DrinkRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = DrinkRepository()

fun Route.getAllDrink() {
    get("/drinks") {
        call.respond(HttpStatusCode.OK, repository.getAllDrink())
    }
}

fun Route.getDrink() {
    get("/drink/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val drink = repository.getDrink(name)

        if (drink == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no drink for the provided name:$name"
            )
        } else {
            call.respond(drink)
        }
    }
}

fun Route.addDrink() {
    get("/add/drink/id={name}") {
        val name = call.parameters["name"]
        call.respond(HttpStatusCode.OK, repository.addDrink(DrinkToDraft(name!!)))
    }
}

fun Route.updateDrink() {
    get("/update/drink/id={name}/new_name={n_name}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateDrink(name, DrinkToDraft(newName!!))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no drink with the name:$name")
        }
    }
}

fun Route.removeDrink() {
    get("/remove/drink/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeDrink(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no drink with the name:$name")
        }
    }
}