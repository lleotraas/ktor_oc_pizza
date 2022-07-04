package fr.lleotraas.model

import kotlinx.serialization.Serializable

@Serializable
class Restaurant(
    val name: String,
    val address: String
)