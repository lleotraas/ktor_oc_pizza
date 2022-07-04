package fr.lleotraas.route

import fr.lleotraas.entities.UserToDraft
import fr.lleotraas.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = UserRepository()

fun Route.getUsers() {
    get("/users"){
        call.respond(HttpStatusCode.OK, repository.getAllUser())
    }
}

fun Route.addUser() {
    get("/add/user/firstname={fname}/lastname={lname}/phone={pnumber}/address={location}/role={employment}") {
        val firstname = call.parameters["fname"]
        val lastname = call.parameters["lname"]
        val phoneNumber = call.parameters["pnumber"]
        val address = call.parameters["location"]
        val role = call.parameters["employment"]
        call.respond(HttpStatusCode.OK, repository.addUser(UserToDraft(firstname!!, lastname!!, phoneNumber!!, address!!, role!!)))
    }
}

fun Route.getUser() {
    get("/user/identification={id}") {
        val userId = call.parameters["id"]?.toIntOrNull()

        if (userId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter has to be a number"
            )
            return@get
        }
        val user = repository.getUser(userId)

        if (user == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no todo for the provided id $userId"
            )
        } else {
            call.respond(user)
        }
    }
}

fun Route.removeUser() {
    get("/remove/identification={id}"){
        val userId = call.parameters["id"]?.toIntOrNull()

        if (userId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter has to be a number"
            )
            return@get
        }
        val removed = repository.removeUser(userId)
        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")

        } else {
            call.respond(HttpStatusCode.NotFound, "found no user with the id $userId")

        }
    }

}

fun Route.updateUser() {
    get("/update/identification={id}/firstname={fname}/lastname={lname}/phone={pnumber}/address={location}/role={employment}") {
        val userId = call.parameters["id"]?.toIntOrNull()
        val firstname = call.parameters["fname"]
        val lastname = call.parameters["lname"]
        val phoneNumber = call.parameters["pnumber"]
        val address = call.parameters["location"]
        val role = call.parameters["employment"]

        if (userId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter has to be a number"
            )
            return@get
        }
        val updated = repository.updateUser(userId, UserToDraft(firstname!!, lastname!!, phoneNumber!!, address!!, role!!))
        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no user with the id $userId")
        }
    }
}