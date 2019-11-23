package com.psd3.data.source

import com.psd3.MissingArgumentException
import com.psd3.model.offer.Offer
import com.psd3.model.offer.OfferRequest
import com.psd3.model.offer.Offers
import com.psd3.data.DatabaseFactory.dbQuery
import com.psd3.data.repository.OffersRepository
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.joda.time.DateTime

class OffersSource : OffersRepository {

    override suspend fun getOffers(page: Int, perPage: Int, name: String?): List<Offer> {
        val result = mutableListOf<Offer>()
        dbQuery {
            when {
                name != null -> {
                    Offers.selectAll().andWhere {
                        Offers.name like "$name%"
                    }
                }
                else -> {
                    Offers.selectAll().limit(perPage, page * perPage)
                }
            }.forEach {
                result.add(Offer.fromRow(it))
            }
        }
        return result
    }

    override suspend fun addOffer(newOffer: OfferRequest): String {
        return dbQuery {
            Offers.insert {
                it[name] = newOffer.name ?: throw MissingArgumentException("name")
                it[description] = newOffer.description ?: throw MissingArgumentException("description")
                it[dateCreated] = DateTime.now()
            } get (Offers.id)
        }.toString()
    }
}