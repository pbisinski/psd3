package com.psd3.model.offer

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Offers : Table() {
    val id = long("id").autoIncrement().primaryKey()
    val ownerId = long("ownerId")
    val name = varchar("name", 60)
    val description = varchar("description", 255)
    val dateCreated = varchar("dateCreated", 100)
    val location = varchar("location", 100)
    val category = integer("category")
}

data class Offer(
    val id: Long,
    val ownerId: Long,
    val name: String,
    val description: String,
    val dateCreated: String,
    val location: String,
    val category: Int
) {

    companion object {
        fun fromRow(row: ResultRow): Offer = with(Offers) {
            Offer(
                id = row[id],
                ownerId = row[ownerId],
                name = row[name],
                description = row[description],
                dateCreated = row[dateCreated],
                location = row[location],
                category = row[category]
            )
        }
    }
}