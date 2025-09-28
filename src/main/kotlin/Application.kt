package com.example

import com.example.presentation.config.configureLogging
import com.example.presentation.config.configureRouting
import com.example.presentation.config.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing
import io.ktor.server.routing.get

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    configureRouting()
    configureLogging()
}
