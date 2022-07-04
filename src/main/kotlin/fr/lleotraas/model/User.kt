package fr.lleotraas.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val firstname: String,
    val lastName: String,
    val phoneNumber: String,
    val address: String,
    val role: String
)