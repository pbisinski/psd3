package com.psd3

import io.ktor.application.*
import io.ktor.server.netty.EngineMain
import org.koin.Logger.SLF4JLogger
import org.koin.ktor.ext.Koin

fun Application.mainModule() {
    // Dependency injection
    install(Koin) {
        SLF4JLogger()
        modules(appModule)
    }
    // Start all services
    services()
}

fun main(args: Array<String>): Unit = EngineMain.main(args)