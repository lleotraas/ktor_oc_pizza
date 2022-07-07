package fr.lleotraas.route

import fr.lleotraas.entities.DessertToDraft
import fr.lleotraas.repository.DessertRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = DessertRepository()

fun Route.getAllDessert() {
    get("/desserts") {
        call.respond(HttpStatusCode.OK, repository.getAllDessert())
    }
}

fun Route.getDessert() {
    get("/dessert/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val dessert = repository.getDessert(name)

        if (dessert == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no dessert for the provided name:$name"
            )
        } else {
            call.respond(dessert)
        }
    }
}

fun Route.addDessert() {
    get("/add/dessert/id={name}") {
        val name = call.parameters["name"]
        call.respond(HttpStatusCode.OK, repository.addDessert(DessertToDraft(name!!)))
    }
}

fun Route.updateDessert() {
    get("/update/dessert/id={name}/new_name={n_name}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateDessert(name, DessertToDraft(newName!!))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no dessert with the name:$name")
        }
    }
}

fun Route.removeDessert() {
    get("/remove/dessert/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeDessert(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no dessert with the name:$name")
        }
    }
}