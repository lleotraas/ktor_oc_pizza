package fr.lleotraas.route

import fr.lleotraas.entities.PizzaToDraft
import fr.lleotraas.repository.PizzaRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = PizzaRepository()

fun Route.getAllPizza() {
    get("/pizzas") {
        call.respond(HttpStatusCode.OK, repository.getAllPizza())
    }
}

fun Route.getPizza() {
    get("/pizza/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val pizza = repository.getPizza(name)

        if (pizza == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no pizza for the provided name:$name"
            )
        } else {
            call.respond(pizza)
        }
    }
}

fun Route.addPizza() {
    get("/add/pizza/id={name}/recipe={text}") {
        val name = call.parameters["name"]
        val recipe = call.parameters["text"]
        call.respond(HttpStatusCode.OK, repository.addPizza(PizzaToDraft(
            name!!,
            recipe!!
        )))
    }
}

fun Route.updatePizza() {
    get("/update/pizza/id={name}/new_name={n_name}/recipe={text}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]
        val recipe = call.parameters["text"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updatePizza(name, PizzaToDraft(
            newName!!,
            recipe!!
        ))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no pizza with the name:$name")
        }
    }
}

fun Route.removePizza() {
    get("/remove/pizza/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removePizza(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no pizza with the name:$name")
        }
    }
}