package com.psd3.service

import com.psd3.data.repository.OffersRepository
import com.psd3.model.offer.OfferRequest
import com.psd3.utils.deserializeJson
import io.ktor.application.ApplicationCall
import io.ktor.response.respond

class OffersService(private val repository: OffersRepository) {

    suspend fun getOffers(context: ApplicationCall) {
        val params = context.request.queryParameters
        context.respond(
            repository.getOffers(
                params["page"]?.toInt() ?: 0,
                params["perPage"]?.toInt() ?: 20,
                params["name"]
            )
        )
    }

    suspend fun addOffer(context: ApplicationCall) {
        with(context) {
            respond(
                repository.addOffer(
                    deserializeJson(OfferRequest::class.java)
                )
            )
        }
    }
}