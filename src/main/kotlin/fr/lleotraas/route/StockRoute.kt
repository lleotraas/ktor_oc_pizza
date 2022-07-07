package fr.lleotraas.route

import fr.lleotraas.entities.StockToDraft
import fr.lleotraas.repository.StockRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = StockRepository()

fun Route.getAllStock() {
    get("/stocks") {
        call.respond(HttpStatusCode.OK, repository.getAllStock())
    }
}

fun Route.getStock() {
    get("/stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val stock = repository.getStock(name)

        if (stock == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no stock for the provided name:$name"
            )
        } else {
            call.respond(stock)
        }
    }
}

fun Route.addStock() {
    get("/add/stock/id={name}/remaining_quantity={rq}") {
        val name = call.parameters["name"]
        val remainingQuantity = call.parameters["rq"]?.toIntOrNull()

        call.respond(HttpStatusCode.OK, repository.addStock(StockToDraft(
            name!!,
            remainingQuantity!!
        )))
    }
}

fun Route.updateStock() {
    get("/update/dessert/id={name}/new_name={n_name}/remaining_quantity={rq}") {
        val name = call.parameters["name"]
        val newName = call.parameters["n_name"]
        val remainingQuantity = call.parameters["rq"]?.toIntOrNull()

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateStock(name, StockToDraft(
            newName!!,
            remainingQuantity!!
        ))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no stock with the name:$name")
        }
    }
}

fun Route.removeStock() {
    get("/remove/stock/id={name}") {
        val name = call.parameters["name"]

        if (name == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeStock(name)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no stock with the name:$name")
        }
    }
}