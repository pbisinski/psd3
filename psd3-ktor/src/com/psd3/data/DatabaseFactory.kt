package com.psd3.data

import com.psd3.AppConfig
import com.psd3.model.offer.Offers
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun connect() {
        Database.connect(dataSource())
    }

    fun init() {
        transaction {
            SchemaUtils.create(Offers)
        }
    }

    fun drop() {
        transaction {
            SchemaUtils.drop(Offers)
        }
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction {
                addLogger(StdOutSqlLogger)
                block()
            }
        }

    private fun dataSource(): HikariDataSource =
        with(HikariConfig()) {
            jdbcUrl = AppConfig.DB_URL
            username = AppConfig.DB_USER
            password = AppConfig.DB_PASS
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
            HikariDataSource(this)
        }
}