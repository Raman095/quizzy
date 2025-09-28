package com.example.presentation.route

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.root() {
    get ("/") {
        call.respondText(
            text = "Welcome to the Quiz API"
        )
    }
}