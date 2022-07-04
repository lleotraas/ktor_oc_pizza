package fr.lleotraas.model

import kotlinx.serialization.Serializable

@Serializable
class Pizza(
    val name: String,
    val recipe: String
)