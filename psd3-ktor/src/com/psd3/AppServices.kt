package com.psd3

import com.psd3.routes.maintenance
import com.psd3.routes.offers
import com.psd3.data.DatabaseFactory
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.route
import io.ktor.routing.routing
import java.text.DateFormat

fun Application.services() {
    database()
    rest()
}

fun database() {
    DatabaseFactory.connect()
}

fun Application.rest() {

    install(DefaultHeaders)

    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }

    install(StatusPages) {
        exception<AppGenericException> { ex ->
            call.respond(ex.httpStatusCode, ex.toResponse())
        }
    }

    routing {
        route(API_PATH) {
            offers()
            maintenance()
        }
    }
}

const val API_PATH = "/api/v1"