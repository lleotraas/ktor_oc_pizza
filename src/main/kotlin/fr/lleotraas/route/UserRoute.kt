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

fun Route.accountNameExist() {
    get("/account_name_exist/account_name={an}") {
        val accountName = call.parameters["an"]
        call.respond(HttpStatusCode.OK, repository.accountNameExist(accountName!!))
    }
}

fun Route.getUser() {
    get("/user/account_name={an}") {
        val accountName = call.parameters["an"]

        if (accountName == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter has to be a number"
            )
            return@get
        }
        val user = repository.getUser(accountName)

        if (user == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no todo for the provided id $accountName"
            )
        } else {
            call.respond(user)
        }
    }
}

fun Route.addUser() {
    get("/add/user/account_name={an}/account_password={ap}/firstname={fname}/lastname={lname}/phone={pnumber}/address={location}/role={employment}") {
        val accountName = call.parameters["an"]
        val accountPassword = call.parameters["ap"]
        val firstname = call.parameters["fname"]
        val lastname = call.parameters["lname"]
        val phoneNumber = call.parameters["pnumber"]
        val address = call.parameters["location"]
        val role = call.parameters["employment"]
        call.respond(HttpStatusCode.OK, repository.addUser(UserToDraft(accountName!!, accountPassword!!, firstname!!, lastname!!, phoneNumber!!, address!!, role!!)))
    }
}
fun Route.updateUser() {
    get("/update/account_name={an}/new_account_name={nan}/account_password={ap}/firstname={fname}/lastname={lname}/phone={pnumber}/address={location}/role={employment}") {
        val accountName = call.parameters["an"]
        val newAccountName = call.parameters["nan"]
        val accountPassword = call.parameters["ap"]
        val firstname = call.parameters["fname"]
        val lastname = call.parameters["lname"]
        val phoneNumber = call.parameters["pnumber"]
        val address = call.parameters["location"]
        val role = call.parameters["employment"]

        if (accountName == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter has to be a number"
            )
            return@get
        }
        val updated = repository.updateUser(accountName, UserToDraft(newAccountName!!, accountPassword!!, firstname!!, lastname!!, phoneNumber!!, address!!, role!!))
        if (updated) {
            call.respond(HttpStatusCode.OK, true)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no user with the account_name $accountName")
        }
    }
}

fun Route.removeUser() {
    get("/remove/account_name={an}"){
        val accountName = call.parameters["an"]

        if (accountName == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter has to be a number"
            )
            return@get
        }
        val removed = repository.removeUser(accountName)
        if (removed) {
            call.respond(HttpStatusCode.OK, true)

        } else {
            call.respond(HttpStatusCode.NotFound, "found no user with the id $accountName")

        }
    }

}