package com.psd3

import com.psd3.data.repository.OffersRepository
import com.psd3.data.source.OffersSource
import com.psd3.service.MaintenanceService
import com.psd3.service.OffersService
import org.koin.dsl.module

val appModule = module(createdAtStart = true) {

    single<OffersRepository> { OffersSource() }

    single { MaintenanceService() }
    single { OffersService(repository = get()) }
}