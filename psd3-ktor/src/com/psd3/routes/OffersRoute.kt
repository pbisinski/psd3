package com.psd3.routes

import com.psd3.service.OffersService
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import org.koin.ktor.ext.inject

fun Route.offers() {

    val service by inject<OffersService>()

    route("offers") {
        get {
            service.getOffers(call)
        }
        post {
            service.addOffer(call)
        }
    }
}