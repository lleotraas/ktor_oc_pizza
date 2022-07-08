package fr.lleotraas

import fr.lleotraas.model.Drink
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import fr.lleotraas.plugins.*
import io.ktor.client.call.*

class ApplicationTest {
    @Test
    fun testDrinkRoutes() = testApplication {
        application {
            configureRouting()
        }

        var response = client.get("/drinks")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/drink/id=coca cola 33cl")
        assertEquals(HttpStatusCode.OK, response.status)

        assertEquals("{\"name\":\"coca cola 33cl\"}", response.bodyAsText())
    }
}


