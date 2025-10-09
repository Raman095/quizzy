package com.synac.presentation.presentation.config

import com.synac.presentation.di.koinModule
import io.ktor.server.application.*
import org.koin.core.logger.Level
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger(level = Level.WARNING)
        modules(koinModule)
    }
}