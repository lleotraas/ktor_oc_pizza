package fr.lleotraas.repository

import fr.lleotraas.databse.UserDatabaseManager
import fr.lleotraas.entities.UserToDraft
import fr.lleotraas.model.User

class UserRepository {

    private val database = UserDatabaseManager()

    fun getAllUser(): List<User> {
        return database.getAllUsers().map { User(it.id, it.firstname, it.lastName, it.phoneNumber, it.address, it.role) }
    }

    fun getUser(id: Int): User? {
        return database.getUser(id)?.let { User(it.id, it.firstname, it.lastName, it.phoneNumber, it.address, it.role) }
    }

    fun addUser(draft: UserToDraft): User {
        return database.addUser(draft)
    }

    fun removeUser(id: Int): Boolean {
        return database.removeUser(id)
    }

    fun updateUser(id: Int, draft: UserToDraft): Boolean {
        return database.updateUser(id, draft)
    }
}