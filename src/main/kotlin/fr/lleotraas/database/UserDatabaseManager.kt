package fr.lleotraas.database

import fr.lleotraas.database.Utils.databaseName
import fr.lleotraas.database.Utils.hostname
import fr.lleotraas.database.Utils.password
import fr.lleotraas.database.Utils.username
import fr.lleotraas.entities.DBUserEntity
import fr.lleotraas.entities.DBUserTable
import fr.lleotraas.entities.UserToDraft
import fr.lleotraas.model.User
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.first
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class UserDatabaseManager {

    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllUsers(): List<DBUserEntity> {
        return ktormDatabase.sequenceOf(DBUserTable).toList()
    }

    fun getUser(accountUser: String): DBUserEntity {
        return ktormDatabase.sequenceOf(DBUserTable).first { it.accountName eq accountUser}
    }

    fun accountNameExist(accountName: String): Boolean {
        val userExist = ktormDatabase.sequenceOf(DBUserTable).firstOrNull{ it.accountName eq accountName }
        return userExist?.accountName == accountName
    }

    fun addUser(user: UserToDraft): User {
        ktormDatabase.insert(DBUserTable) {
            set(it.accountName, user.accountName)
            set(it.accountPassword, user.accountPassword)
            set(it.firstname, user.firstname)
            set(it.lastName, user.lastName)
            set(it.phoneNumber, user.phoneNumber)
            set(it.address, user.address)
            set(it.role, user.role)
        }
        return User(user.accountName, user.accountPassword, user.firstname, user.lastName, user.phoneNumber, user.address, user.role)
    }

    fun updateUser(accountName: String, user: UserToDraft): Boolean {
        val updateROws = ktormDatabase.update(DBUserTable) {
            set(it.accountName, user.accountName)
            set(it.accountPassword, user.accountPassword)
            set(it.firstname, user.firstname)
            set(it.lastName, user.lastName)
            set(it.phoneNumber, user.phoneNumber)
            set(it.address, user.address)
            set(it.role, user.role)
            where {
                it.accountName eq accountName
            }
        }
        return updateROws > 0
    }

    fun removeUser(accountName: String): Boolean {
        val deletedRows = ktormDatabase.delete(DBUserTable) {
            it.accountName eq accountName
        }
        return deletedRows > 0
    }
}