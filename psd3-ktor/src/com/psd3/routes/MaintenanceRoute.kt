package com.psd3.routes

import com.psd3.service.MaintenanceService
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import org.koin.ktor.ext.inject

fun Route.maintenance() {

    val service by inject<MaintenanceService>()

    route("test") {
        get {
            service.healthCheck(call)
        }
        route("setup") {
            get {
                service.setupDatabase(call)
            }
        }
    }
}