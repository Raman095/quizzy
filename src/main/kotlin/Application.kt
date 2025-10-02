package com.synac.presentation

import com.synac.presentation.presentation.config.configureLogging
import com.synac.presentation.presentation.config.configureRouting
import com.synac.presentation.presentation.config.configureSerialization
import com.synac.presentation.presentation.config.configureStatusPages
import com.synac.presentation.presentation.config.configureValidation
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureStatusPages()
    configureValidation()
    configureSerialization()
    configureRouting()
    configureLogging()
}
