package com.psd3.data.repository

import com.psd3.model.offer.Offer
import com.psd3.model.offer.OfferRequest

interface OffersRepository {

    suspend fun getOffers(page: Int, perPage: Int, name: String? = null): List<Offer>

    suspend fun addOffer(newOffer: OfferRequest): String
}