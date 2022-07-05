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
import org.ktorm.dsl.delete
import org.ktorm.dsl.eq
import org.ktorm.dsl.insertAndGenerateKey
import org.ktorm.dsl.update
import org.ktorm.entity.first
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

    fun getUser(id: Int): DBUserEntity {
        return ktormDatabase.sequenceOf(DBUserTable).first { it.id eq id }
    }

    fun addUser(user: UserToDraft): User {
        val insertedId = ktormDatabase.insertAndGenerateKey(DBUserTable) {
            set(it.firstname, user.firstname)
            set(it.lastName, user.lastName)
            set(it.phoneNumber, user.phoneNumber)
            set(it.address, user.address)
            set(it.role, user.role)
        } as Int
        return User(insertedId, user.firstname, user.lastName, user.phoneNumber, user.address, user.role)
    }

    fun updateUser(id: Int, user: UserToDraft): Boolean {
        val updateROws = ktormDatabase.update(DBUserTable) {
            set(it.firstname, user.firstname)
            set(it.lastName, user.lastName)
            set(it.phoneNumber, user.phoneNumber)
            set(it.address, user.address)
            set(it.role, user.role)
            where {
                it.id eq id
            }
        }
        return updateROws > 0
    }

    fun removeUser(id: Int): Boolean {
        val deletedRows = ktormDatabase.delete(DBUserTable) {
            it.id eq id
        }
        return deletedRows > 0
    }
}