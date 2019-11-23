package com.psd3.model.offer

import org.jetbrains.exposed.sql.Table

object Offers : Table() {
    val id = long("id").autoIncrement().primaryKey()
    val name = varchar("name", 60)
    val description = varchar("description", 255)
    val dateCreated = datetime("dateCreated")
}