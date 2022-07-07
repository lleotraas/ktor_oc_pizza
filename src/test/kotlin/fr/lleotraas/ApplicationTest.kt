package fr.lleotraas

import fr.lleotraas.model.Drink
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.locations.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import fr.lleotraas.plugins.*
import io.ktor.client.call.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/desserts").apply {
            val drinks = body<List<Drink>>()
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("coca cola 33cl", drinks[0].name)
        }
    }
}