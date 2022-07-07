package fr.lleotraas.plugins

import fr.lleotraas.route.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
//    install(Locations) {}

    routing {

        // Dessert
        getAllDessert()
        getDessert()
        addDessert()
        updateDessert()
        removeDessert()

        // DessertStock
        getAllDessertStock()
        getDessertStock()
        addDessertStock()
        updateDessertStock()
        removeDessertStock()

        // Drink
        getAllDrink()
        getDrink()
        addDrink()
        updateDrink()
        removeDrink()

        // DrinkStock
        getAllDrinkStock()
        getDrinkStock()
        addDrinkStock()
        updateDrinkStock()
        removeDrinkStock()

        // IngredientStock
        getAllIngredientStock()
        getIngredientStock()
        addIngredientStock()
        updateIngredientStock()
        removeIngredientStock()

        // PizzaOrder
        getAllPizzaOrder()
        getPizzaOrder()
        addPizzaOrder()
        updatePizzaOrder()
        removePizzaOrder()

        // Pizza
        getAllPizza()
        getPizza()
        addPizza()
        updatePizza()
        removePizza()

        // ProductOrder
        getAllProductOrder()
        getProductOrder()
        addProductOrder()
        updateProductOrder()
        removeProductOrder()

        // Product
        getAllProduct()
        getProduct()
        addProduct()
        updateProduct()
        removeProduct()

        // ProductStock
        getAllProductStock()
        getProductStock()
        addProductStock()
        updateProductStock()
        removeProductStock()

        // Restaurant
        getAllRestaurant()
        getRestaurant()
        addRestaurant()
        updateRestaurant()
        removeRestaurant()

        // Stock
        getAllStock()
        getStock()
        addStock()
        updateStock()
        removeStock()

        // User
        getUsers()
        getUser()
        addUser()
        updateUser()
        removeUser()

    }
}

