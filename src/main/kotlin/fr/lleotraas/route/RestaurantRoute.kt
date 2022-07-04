package fr.lleotraas.route

import fr.lleotraas.entities.RestaurantToDraft
import fr.lleotraas.repository.RestaurantRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = RestaurantRepository()

fun Route.getAllRestaurant() {
    get("/restaurants") {
        call.respond(HttpStatusCode.OK, repository.getAllRestaurant())
    }
}

fun Route.getRestaurant() {
    get("/restaurant/id={name}") {
        val restaurantName = call.parameters["name"]

        if (restaurantName == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val restaurant = repository.getRestaurant(restaurantName)

        if (restaurant == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no restaurant for the provided name:$restaurantName"
            )
        } else {
            call.respond(restaurant)
        }
    }
}

fun Route.addRestaurant() {
    get("/add/restaurant/id={name}/location={address}") {
        val restaurantName = call.parameters["name"]
        val address = call.parameters["address"]
        call.respond(HttpStatusCode.OK, repository.addRestaurant(RestaurantToDraft(restaurantName!!, address!!)))
    }
}

fun Route.updateRestaurant() {
    get("/update/restaurant/id={name}/rname={newname}/location={address}") {
        val restaurantName = call.parameters["name"]
        val newName = call.parameters["newname"]
        val address = call.parameters["address"]

        if (restaurantName == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updateRestaurant(restaurantName, RestaurantToDraft(newName!!, address!!))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no restaurant with the name:$restaurantName")
        }
    }
}

fun Route.removeRestaurant() {
    get("/remove/restaurant/id={name}") {
        val restaurantName = call.parameters["name"]

        if (restaurantName == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removeRestaurant(restaurantName)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no restaurant with the name:$restaurantName")
        }
    }
}