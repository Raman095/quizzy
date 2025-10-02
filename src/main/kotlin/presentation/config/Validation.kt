package com.synac.presentation.presentation.config

import com.synac.presentation.presentation.validator.validateQuizQuestion
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation

fun Application.configureValidation() {
    install(RequestValidation) {
        validateQuizQuestion()
    }
}