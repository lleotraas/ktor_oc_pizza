package fr.lleotraas.repository

import fr.lleotraas.database.UserDatabaseManager
import fr.lleotraas.entities.UserToDraft
import fr.lleotraas.model.User

class UserRepository {

    private val database = UserDatabaseManager()

    fun getAllUser(): List<User> {
        return database.getAllUsers().map { User(it.accountName, it.accountPassword, it.firstname, it.lastName, it.phoneNumber, it.address, it.role) }
    }

    fun getUser(accountName: String): User? {
        return database.getUser(accountName)?.let { User(it.accountName, it.accountPassword, it.firstname, it.lastName, it.phoneNumber, it.address, it.role) }
    }

    fun accountNameExist(accountName: String): Boolean {
        return database.accountNameExist(accountName)
    }

    fun addUser(draft: UserToDraft): User {
        return database.addUser(draft)
    }

    fun removeUser(accountName: String): Boolean {
        return database.removeUser(accountName)
    }

    fun updateUser(accountName: String, draft: UserToDraft): Boolean {
        return database.updateUser(accountName, draft)
    }
}