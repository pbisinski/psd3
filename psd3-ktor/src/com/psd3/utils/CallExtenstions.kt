package com.psd3.utils

import com.google.gson.Gson
import io.ktor.application.ApplicationCall
import io.ktor.request.receive

suspend fun <T> ApplicationCall.deserializeJson(type: Class<T>): T = Gson().fromJson(this.receive<String>(), type)