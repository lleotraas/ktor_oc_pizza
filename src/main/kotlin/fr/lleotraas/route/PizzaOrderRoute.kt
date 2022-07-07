package fr.lleotraas.route

import fr.lleotraas.entities.PizzaOrderToDraft
import fr.lleotraas.repository.PizzaOrderRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val repository = PizzaOrderRepository()

fun Route.getAllPizzaOrder() {
    get("/pizza_order") {
        call.respond(HttpStatusCode.OK, repository.getAllPizzaOrder())
    }
}

fun Route.getPizzaOrder() {
    get("/pizza_order/id={identification}") {
        val id = call.parameters["identification"]?.toIntOrNull()

        if (id == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val pizzaOrder = repository.getPizzaOrder(id)

        if (pizzaOrder == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "found no pizza_order for the provided name:$id"
            )
        } else {
            call.respond(pizzaOrder)
        }
    }
}

fun Route.addPizzaOrder() {
    get("/add/pizza_order/id={identification}/user_id={uid}/restaurant_name={r_name}/creation_date={date}/delivery_hour={hour}/delivery_state={state}/is_paid={paid}/is_delivery={delivery}/amount={total}") {
        val id = call.parameters["identification"]?.toIntOrNull()
        val userId = call.parameters["uid"]?.toIntOrNull()
        val restaurantNAme = call.parameters["r_name"]
        val creationDate = call.parameters["date"]
        val deliveryHour = call.parameters["hour"]
        val deliveryState = call.parameters["state"]
        val isPaid = call.parameters["paid"].toBoolean()
        val isDelivery = call.parameters["delivery"].toBoolean()
        val amount = call.parameters["total"]?.toFloat()

        call.respond(HttpStatusCode.OK, repository.addPizzaOrder(PizzaOrderToDraft(
            userId!!,
            restaurantNAme!!,
            creationDate!!,
            deliveryHour!!,
            deliveryState!!,
            isPaid,
            isDelivery,
            amount!!
        )))
    }
}

fun Route.updatePizzaOrder() {
    get("/update/pizza_order/id={identification}/user_id={uid}/restaurant_name={r_name}/creation_date={date}/delivery_hour={hour}/delivery_state={state}/is_paid={paid}/is_delivery={delivery}/amount={total}") {
        val id = call.parameters["identification"]?.toIntOrNull()
        val userId = call.parameters["uid"]?.toIntOrNull()
        val restaurantNAme = call.parameters["r_name"]
        val creationDate = call.parameters["date"]
        val deliveryHour = call.parameters["hour"]
        val deliveryState = call.parameters["state"]
        val isPaid = call.parameters["paid"].toBoolean()
        val isDelivery = call.parameters["delivery"].toBoolean()
        val amount = call.parameters["total"]?.toFloat()

        if (id == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "name parameter hasn't to be empty"
            )
            return@get
        }
        val updated = repository.updatePizzaOrder(id, PizzaOrderToDraft(
            userId!!,
            restaurantNAme!!,
            creationDate!!,
            deliveryHour!!,
            deliveryState!!,
            isPaid,
            isDelivery,
            amount!!
        ))

        if (updated) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound, "found no pizza_order with the name:$id")
        }
    }
}

fun Route.removePizzaOrder() {
    get("/remove/pizza_order/id={identification}") {
        val id = call.parameters["identification"]?.toIntOrNull()

        if (id == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "id parameter hasn't to be empty"
            )
            return@get
        }
        val removed = repository.removePizzaOrder(id)

        if (removed) {
            call.respond(HttpStatusCode.OK, "successfully removed")
        } else {
            call.respond(HttpStatusCode.NotFound, "found no pizza_order with the name:$id")
        }
    }
}