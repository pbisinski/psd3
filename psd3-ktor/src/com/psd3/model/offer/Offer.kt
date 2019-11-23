package com.psd3.model.offer

import org.jetbrains.exposed.sql.ResultRow
import org.joda.time.DateTime

data class Offer(
    val id: Long,
    val name: String,
    val description: String,
    val dateCreated: DateTime
) {

    companion object {
        fun fromRow(row: ResultRow): Offer =
            Offer(
                row[Offers.id].toLong(),
                row[Offers.name],
                row[Offers.description],
                row[Offers.dateCreated]
            )
    }
}