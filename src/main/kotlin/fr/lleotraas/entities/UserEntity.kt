package fr.lleotraas.entities

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBUserTable: Table<DBUserEntity>("user") {
    val id = int("id").primaryKey().bindTo { it.id }
    val accountName = varchar("account_name").bindTo { it.accountName }
    val accountPassword = varchar("account_password").bindTo { it.accountPassword }
    val firstname = varchar("first_name").bindTo { it.firstname }
    val lastName = varchar("last_name").bindTo { it.lastName }
    val phoneNumber = varchar("phone_number").bindTo { it.phoneNumber }
    val address = varchar("address").bindTo { it.address }
    val role = varchar("role").bindTo { it.role }
}

interface DBUserEntity: Entity<DBUserEntity> {
    companion object : Entity.Factory<DBUserEntity>()
    val id: Int
    val accountName: String
    val accountPassword: String
    val firstname: String
    val lastName: String
    val phoneNumber: String
    val address: String
    val role: String
}