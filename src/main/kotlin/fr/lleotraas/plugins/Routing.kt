package fr.lleotraas.plugins

import fr.lleotraas.route.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
//    install(Locations) {}

    routing {

        // User
        getUsers()
        getUser()
        addUser()
        updateUser()
        removeUser()

        // Restaurant
        getAllRestaurant()
        getRestaurant()
        addRestaurant()
        updateRestaurant()
        removeRestaurant()
    }
}

