package fr.lleotraas.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val accountName: String,
    val accountPassword: String,
    val firstname: String,
    val lastName: String,
    val phoneNumber: String,
    val address: String,
    val role: String
)