package com.psd3.service

import com.psd3.data.DatabaseFactory
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.title

class MaintenanceService {

    suspend fun healthCheck(context: ApplicationCall) {
        context.respondHtml {
            head {
                title { +"PSD3 Open API" }
            }
            body {
                h1 {
                    +"PSD3 API is running"
                }
            }
        }
    }

    suspend fun setupDatabase(context: ApplicationCall) {
        with(DatabaseFactory) {
            drop()
            init()
        }
        context.respondHtml {
            head {
                title { +"PSD3 Open API" }
            }
            body {
                h1 {
                    +"You just wiped database"
                }
            }
        }
    }
}